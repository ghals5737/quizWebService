package com.example.quiz.repository;

import com.example.quiz.dto.Quiz;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends CrudRepository<Quiz,Long> {
    Quiz findQuizByQuizNo(Long quizNo);
}
