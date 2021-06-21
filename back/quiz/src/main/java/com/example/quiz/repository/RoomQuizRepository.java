package com.example.quiz.repository;

import com.example.quiz.dto.RoomQuiz;
import com.example.quiz.dto.RoomQuizId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomQuizRepository extends CrudRepository<RoomQuiz, RoomQuizId> {
}
