package com.example.quiz.dto;

import lombok.Data;

import java.util.List;

@Data
public class PrbUserAnswer {
    private long prbNo;
    private List<UserAnswer> userAnswerList;
}
