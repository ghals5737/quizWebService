package com.example.quiz.service;

import com.example.quiz.dto.TokenDto;
import com.example.quiz.dto.User;
import com.example.quiz.exception.RestException;
import com.example.quiz.jwt.JwtFilter;
import com.example.quiz.jwt.TokenProvider;
import com.example.quiz.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service("userService")
public class UserServiceImpl implements UserService{

    @Autowired
    private final UserRepository userRepository;

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User createUser(User user)
    {
        user.setUserPw(passwordEncoder.encode(user.getUserPw()));
        return userRepository.save(user);
    }

    @Override
    public User getUser(Long user) {
        return userRepository.findUserByUserNo(user);
    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User loginUser(User user) {
        String userId=user.getUserId();
        if(userRepository.existsByUserId(userId)){
            User result=userRepository.findUserByUserId(user.getUserId());
            if(result.getUserPw().equals(user.getUserPw())){
                return result;
            }else{
                throw new RestException(HttpStatus.SERVICE_UNAVAILABLE,"invalid userPw");
            }
        }else{
            throw new RestException(HttpStatus.SERVICE_UNAVAILABLE,"invalid userId");
        }
    }

    @Override
    public void deleteUser(long userNo) {
        userRepository.deleteById(userNo);
    }

    @Override
    public ResponseEntity<TokenDto> authenticate(User user) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(user.getUserId(), user.getUserPw());
            System.out.println("?!?");
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            System.out.println("?!@!@?");
            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println("?!@!@?");
            String jwt = tokenProvider.createToken(authentication);
            System.out.println("?!!!!!!!?");
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
            System.out.println("_+_+_+_+_+_+_+_+_+_+_+_+_+_+_\n_+_+_+_+_+_+서비스+_+_+_+_+_+_+_+_\n_+_+_+_+_+_+_+_+_+_+_+_+_+_+_\n");
            User result = userRepository.findUserByUserId(user.getUserId());
            return new ResponseEntity<>(new TokenDto(jwt, result.getUserId(), result.getUserNo()), httpHeaders, HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
