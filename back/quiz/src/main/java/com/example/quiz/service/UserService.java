package com.example.quiz.service;

import com.example.quiz.domain.user.User;
import com.example.quiz.dto.user.request.UserCreateRequest;
import com.example.quiz.dto.user.request.UserUpdateRequest;
import com.example.quiz.global.error.code.ErrorCode;
import com.example.quiz.global.error.exception.CustomException;
import com.example.quiz.repository.user.UserQuerydslRepository;
import com.example.quiz.repository.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private UserQuerydslRepository userQuerydslRepository;

    @Transactional
    public void saveUser(UserCreateRequest request){
        User newUser=request.toUser();
    }

    @Transactional
    public void updateUser(UserUpdateRequest request){
        User user=userRepository.findById(request.getUserNo()).orElseThrow(()->new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        user.updateUserId(request.getUserId());
    }

    @Transactional
    public void deleteUser(String userId) {
        User user=userQuerydslRepository.find(userId).orElseThrow(()->new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        userRepository.delete(user);
    }
}
