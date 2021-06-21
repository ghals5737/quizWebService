package com.example.quiz.dto;

import lombok.Data;

import java.util.List;

@Data
public class RoomQuizNo {
    private long roomNo;
    private List<Long> quizNoList;
}
