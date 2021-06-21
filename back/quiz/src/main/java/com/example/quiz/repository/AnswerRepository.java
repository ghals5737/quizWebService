package com.example.quiz.repository;

import com.example.quiz.dto.Answer;
import com.example.quiz.dto.AnswerId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends CrudRepository<Answer, AnswerId> {
}
