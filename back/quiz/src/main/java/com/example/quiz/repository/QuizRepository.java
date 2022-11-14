package com.example.quiz.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<Quiz,Long> {
    Quiz findQuizByQuizNo(Long quizNo);
    Page<Quiz> findAll(Pageable pageable);
}
