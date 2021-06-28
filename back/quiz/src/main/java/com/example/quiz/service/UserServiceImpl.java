package com.example.quiz.service;

import com.example.quiz.dto.User;
import com.example.quiz.exception.RestException;
import com.example.quiz.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service("userService")
public class UserServiceImpl implements UserService{

    @Autowired
    private final UserRepository userRepository;

    @Override
    public User createUser(User user) {
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
}
