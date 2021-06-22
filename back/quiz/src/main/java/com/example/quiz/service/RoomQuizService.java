package com.example.quiz.service;

import com.example.quiz.dto.Quiz;
import com.example.quiz.dto.Room;
import com.example.quiz.dto.RoomQuizNo;

import java.util.List;

public interface RoomQuizService {
    void createRoomQuiz(RoomQuizNo roomQuizNo);
    List<Quiz> findAllByRoomNo(long roomNo);
    void deleteRoomQuizsByQuiz(Long quizNo);
    void deleteRoomQuizsByRoom(Long roomNo);
}
