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

import java.util.ArrayList;
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
    public User createUser(User userdto)
    {
        User user=User.builder()
                .userId(userdto.getUserId())
                .userPw(passwordEncoder.encode(userdto.getUserPw()))
                .authorityName("ROLE_USER")
                .build();
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
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = tokenProvider.createToken(authentication);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
            User result = userRepository.findUserByUserId(user.getUserId());
            return new ResponseEntity<>(new TokenDto(jwt, result.getUserId(), result.getUserNo()), httpHeaders, HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<String> getUserNameByUserNo(List<Long> userNoList) {
        ArrayList<String> result=new ArrayList<>();
        for(Long userNo:userNoList){
            result.add(userRepository.findUserByUserNo(userNo).getUserId());
        }
        return result;
    }
}
