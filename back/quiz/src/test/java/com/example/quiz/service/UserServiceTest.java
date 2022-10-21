package com.example.quiz.service;

import com.example.quiz.dto.User;
import com.example.quiz.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDateTime;
import java.util.List;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
@NoArgsConstructor
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @AfterEach
    public void clear(){
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("회원 가입이 정상 작동한다")
    void createUserTest(){
        //given
        User saveTestUser=User.builder()
                .userId("saveTest")
                .userPw("saveTest")
                .regTime(LocalDateTime.now())
                .build();
        //when
        userRepository.save(saveTestUser);

        //then
        List<User> results=userRepository.findAll();
        assertThat(results).hasSize(1);
        assertThat(results.get(0).getUserId()).isEqualTo(saveTestUser.getUserId());
        assertThat(results.get(0).getUserPw()).isEqualTo(saveTestUser.getUserPw());
    }

    @Test
    @DisplayName("유저 전체 조회가 정상 작동한다.")
    void getAllUsers(){
        //given
        User saveTestUserA=User.builder()
                .userId("saveTest")
                .userPw("saveTest")
                .regTime(LocalDateTime.now())
                .build();
        User saveTestUserB=User.builder()
                .userId("saveTest")
                .userPw("saveTest")
                .regTime(LocalDateTime.now())
                .build();
        userRepository.save(saveTestUserA);
        userRepository.save(saveTestUserB);

        //when
        List<User> results=userRepository.findAll();

        //then
        assertThat(results).hasSize(2);
        assertThat(results)
                .extracting("userId")
                .containsExactlyInAnyOrder(saveTestUserA.getUserId(),saveTestUserB.getUserId());
        assertThat(results)
                .extracting("userPw")
                .containsExactlyInAnyOrder(saveTestUserA.getUserPw(),saveTestUserB.getUserPw());
    }



}
