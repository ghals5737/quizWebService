package com.example.quiz.dto.user.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
public class UserCreateRequest {
    private String userId;
    private String userPw;
}
