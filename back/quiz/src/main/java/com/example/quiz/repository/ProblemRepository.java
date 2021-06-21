package com.example.quiz.repository;

import com.example.quiz.dto.Problem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProblemRepository extends CrudRepository<Problem,Long> {
}
