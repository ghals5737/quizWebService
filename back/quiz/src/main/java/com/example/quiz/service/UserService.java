package com.example.quiz.service;

import com.example.quiz.domain.user.User;
import com.example.quiz.dto.user.request.UserCreateRequest;
import com.example.quiz.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;

    @Transactional
    void saveUser(UserCreateRequest request){
        User newUser=User.toUser(request.getUserId(),request.getUserPw());
    }
}
