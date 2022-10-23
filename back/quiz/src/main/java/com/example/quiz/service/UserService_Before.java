package com.example.quiz.service;

import com.example.quiz.dto.TokenDto;
import com.example.quiz.domain.user.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService_Before {
    User createUser(User user);
    User getUser(Long user);
    List<User> getAllUsers();
    User updateUser(User user);
    User loginUser(User user);
    void deleteUser(long userNo);
    ResponseEntity<TokenDto>  authenticate(User user);
    List<String> getUserNameByUserNo(List<Long> userNoList);
}
