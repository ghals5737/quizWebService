package com.example.quiz.service;

import com.example.quiz.domain.user.User;
import com.example.quiz.dto.user.request.UserCreateRequest;
import com.example.quiz.dto.user.request.UserUpdateRequest;
import com.example.quiz.repository.UserRepository;
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
        UserCreateRequest request=UserCreateRequest.builder().userId("saveTest").userPw("saveTest").build();
        //when
        userService.saveUser(request);

        //then
        List<User> results=userRepository.findAll();
        assertThat(results).hasSize(1);
        assertThat(results.get(0).getUserId()).isEqualTo("saveTest");
        assertThat(results.get(0).getUserPw()).isEqualTo("saveTest");
    }

    @Test
    @DisplayName("유저 전체 조회가 정상 작동한다.")
    void getAllUsers(){
        //given
        User saveTestUserA=User.builder()
                .userId("saveTestA")
                .userPw("saveTestA")
                .regTime(LocalDateTime.now())
                .build();
        User saveTestUserB=User.builder()
                .userId("saveTestB")
                .userPw("saveTestB")
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

    @Test
    @DisplayName("유저 업데이트가 정상 작동한다.")
    void updateUser(){
        //given
        User savedUser=userRepository.save(User.builder()
                .userId("saveTest")
                .userPw("saveTest")
                .regTime(LocalDateTime.now())
                .build());
        UserUpdateRequest request=UserUpdateRequest.builder().userNo(savedUser.getUserNo()).userId("updateTest").build();

        //when
        userService.updateUser(request);

        //when
        List<User> results=userRepository.findAll();

        //then
        assertThat(results).isEqualTo("updateTest");
    }

    @Test
    @DisplayName("유저 삭제가 정상 작동한다.")
    void deleteUser(){
        //given

    }

    @Test
    @DisplayName("유저 로그인이 정상 작동한다.")
    void loginUserTest(){

    }

    @Test
    @DisplayName("유저 토큰 인증이 정상 작동한다.")
    void authenticateTest(){}

    @Test
    @DisplayName("")
    void getUserNameByUserNoTest(){}
}
