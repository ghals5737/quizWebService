package com.example.quiz.service;

import com.example.quiz.dto.Quiz;

import java.util.Optional;

public interface QuizService {
    Quiz createQuiz(Quiz quiz);
    Quiz getQuizByNo(Long quizNo);
    void deleteQuiz(long quizNo);
}
