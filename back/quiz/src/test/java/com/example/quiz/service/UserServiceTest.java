package com.example.quiz.service;

import com.example.quiz.dto.User;
import com.example.quiz.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
@AllArgsConstructor
@NoArgsConstructor
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    private User saveTestUser;

    @BeforeEach
    public void init(){
        saveTestUser=User.builder()
                .userId("saveTest")
                .userPw("saveTest")
                .regTime(LocalDateTime.now())
                .build();
        userRepository.save(
                User.builder()
                .userId("test")
                .userPw("test")
                .regTime(LocalDateTime.now())
                .build());
    }

    @AfterEach
    public void clear(){
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("회원 가입이 정상 작동한다")
    void createUserTest(){
        userRepository.save(saveTestUser);
    }

}
