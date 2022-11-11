package com.example.quiz.dto.user.request;

import com.example.quiz.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
public class UserCreateRequest {
    private String userId;
    private String userPw;

    public User toUser(){
        return User.builder()
                .userId(userId)
                .userPw(userPw)
                .regTime(LocalDateTime.now())
                .build();
    }
}
