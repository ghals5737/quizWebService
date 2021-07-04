package com.example.quiz.repository;

import com.example.quiz.dto.Answer;
import com.example.quiz.dto.AnswerId;
import com.example.quiz.dto.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends CrudRepository<Answer, AnswerId> {
    List<Answer> findAllByRoomNoAndUser(long roomNo, User user);
}
