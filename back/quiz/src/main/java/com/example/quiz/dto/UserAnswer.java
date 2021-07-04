package com.example.quiz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAnswer {
    private Long prbNo;
    private int score;
    private String answer;
    private List<String> submitAnswer;
}
