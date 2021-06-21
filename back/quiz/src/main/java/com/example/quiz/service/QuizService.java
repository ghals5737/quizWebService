package com.example.quiz.service;

import com.example.quiz.dto.Quiz;

import java.util.List;

public interface QuizService {
    Quiz createQuiz(Quiz quiz);
    List<Quiz> finfByRoomNo(long roomNo);
}
