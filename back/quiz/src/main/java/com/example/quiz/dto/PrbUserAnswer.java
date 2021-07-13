package com.example.quiz.dto;

import lombok.Data;

import java.util.List;

@Data
public class PrbUserAnswer {
    private long userNo;
    private long roomNo;
    private String userId;
    private List<UserAnswer> userAnswerList;
}
