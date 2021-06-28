package com.example.quiz.service;

import com.example.quiz.dto.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    User getUser(Long user);
    List<User> getAllUsers();
    User updateUser(User user);
    User loginUser(User user);
    void deleteUser(long userNo);
}
