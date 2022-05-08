package com.example.quiz.service;

import com.example.quiz.dto.User;
import com.example.quiz.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private User user;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        user= User.builder()
                .userId("testId")
                .userPw("testPw")
                .userNo(0L)
                .authorityName("ROLE_USER")
                .build();
    }

    @Test
    void userCreateTest() throws Exception {
        when(userRepository.existsByUserId("testId")).thenReturn(false);
        when(passwordEncoder.encode("testPw")).thenReturn("testPw");
        when(userRepository.save(user)).thenReturn(user);

        User result=userService.createUser(user);

        assertThat(result).isEqualTo(user);
    }
}
