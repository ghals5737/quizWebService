package com.example.quiz.controller;

import com.example.quiz.dto.Quiz;
import com.example.quiz.dto.Room;
import com.example.quiz.exception.RestException;
import com.example.quiz.service.QuizService;
import com.example.quiz.service.RoomQuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/quiz")
@RestController
public class QuizController {
    @Resource(name = "quizService")
    private final QuizService quizService;

    @Resource(name = "roomQuizService")
    private final RoomQuizService roomQuizService;

    @GetMapping("/room-quizs")
    private List<Quiz> getQuizListByRoom(Long roomNo){
        return roomQuizService.findAllByRoomNo(roomNo);
    }

    @GetMapping()
    private Quiz getQuizByNo(Long quizNo){
        return quizService.getQuizByNo(quizNo);
    }

    @PostMapping()
    private Quiz insert(@RequestBody Quiz quiz){
        return quizService.createQuiz(quiz);
    }

    @DeleteMapping()
    private void deleteQuiz(Long quizNo){
        try{
            roomQuizService.deleteRoomQuizsByQuiz(quizNo);
            quizService.deleteQuiz(quizNo);
        }catch (Exception e){
            throw new RestException(HttpStatus.SERVICE_UNAVAILABLE,e.getMessage());
        }
    }
}
