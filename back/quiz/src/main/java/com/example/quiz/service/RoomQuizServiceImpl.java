package com.example.quiz.service;

import com.example.quiz.dto.Quiz;
import com.example.quiz.dto.Room;
import com.example.quiz.dto.RoomQuiz;
import com.example.quiz.repository.RoomQuizRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service("roomQuizService")
public class RoomQuizServiceImpl implements RoomQuizService{
    @Autowired
    private  final RoomQuizRepository roomQuizRepository;

    @Override
    public void createRoomQuiz(List<Long> quizNoList) {
        long roomNo=quizNoList.get(0);
        quizNoList.remove(0);
        quizNoList.forEach(el->{
            RoomQuiz roomQuiz=new RoomQuiz();
            Room room=new Room();
            room.setRoomNo(roomNo);
            Quiz quiz=new Quiz();
            quiz.setQuizNo(el);
            roomQuiz.setRoom(room);
            roomQuiz.setQuiz(quiz);
            roomQuizRepository.save(roomQuiz);
        });
    }
}
