package com.example.quiz.service;

import com.example.quiz.dto.Quiz;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;

public interface QuizService {
    Quiz createQuiz(Quiz quiz);
    Quiz getQuizByNo(Long quizNo);
    void deleteQuiz(long quizNo);
    List<Quiz> findAllByPageable(Pageable pageable);
    Long getTotal();
}
