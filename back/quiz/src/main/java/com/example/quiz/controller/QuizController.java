package com.example.quiz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/quiz")
@RestController
public class QuizController {
//    @Resource(name = "quizService")
//    private final QuizService quizService;
//
//    @Resource(name = "roomQuizService")
//    private final RoomQuizService roomQuizService;
//
//    @GetMapping("/room-quizs")
//    private List<Quiz> getQuizListByRoom(Long roomNo){
//        return roomQuizService.findAllByRoomNo(roomNo);
//    }
//
//    @GetMapping("/total")
//    private Long getTotal(){
//        return quizService.getTotal();
//    }
//
//    @GetMapping("/allquiz")
//    private List<Quiz> findAllByPageable(int page,int size){
//        return quizService.findAllByPageable(PageRequest.of(page,size));
//    }
//
//    @GetMapping()
//    private Quiz getQuizByNo(Long quizNo){
//        return quizService.getQuizByNo(quizNo);
//    }
//
//    @PostMapping()
//    private Quiz insert(@RequestBody Quiz quiz){
//        return quizService.createQuiz(quiz);
//    }
//
//    @DeleteMapping()
//    private void deleteQuiz(Long quizNo){
//        try{
//            roomQuizService.deleteRoomQuizsByQuiz(quizNo);
//            quizService.deleteQuiz(quizNo);
//        }catch (Exception e){
//            throw new RestException(HttpStatus.SERVICE_UNAVAILABLE,e.getMessage());
//        }
//    }
}
