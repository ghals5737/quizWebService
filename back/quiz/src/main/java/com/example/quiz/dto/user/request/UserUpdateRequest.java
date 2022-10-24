package com.example.quiz.dto.user.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserUpdateRequest {
    private long userNo;
    private String userId;
}
