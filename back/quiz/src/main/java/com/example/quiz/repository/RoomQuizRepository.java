package com.example.quiz.repository;

import com.example.quiz.dto.RoomQuiz;
import com.example.quiz.dto.RoomQuizId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomQuizRepository extends CrudRepository<RoomQuiz, RoomQuizId> {
//    List<RoomQuiz> findRoomQuizByRoomOrderByQuizOrder(Room room);
//    @Transactional
//    void deleteRoomQuizsByQuiz(Quiz quiz);
//    @Transactional
//    void deleteRoomQuizsByRoom(Room room);
}
