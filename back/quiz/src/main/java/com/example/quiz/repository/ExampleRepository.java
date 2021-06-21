package com.example.quiz.repository;

import com.example.quiz.dto.Example;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExampleRepository extends CrudRepository<Example,Long> {
}
