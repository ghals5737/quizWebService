package com.example.quiz.controller;

import com.example.quiz.dto.Quiz;
import com.example.quiz.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/quiz")
@RestController
public class QuizController {
    @Autowired
    private QuizService quizService;

    @GetMapping("/{roomNo}")
    private List<Quiz> getQuizListByRoom(@PathVariable(value = "roomNo") long roomNo){
        return null;
    }

    @PostMapping()
    private Quiz insert(@RequestBody Quiz quiz){
        return quizService.createQuiz(quiz);
    }
}
