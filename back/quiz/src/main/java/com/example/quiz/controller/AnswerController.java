package com.example.quiz.controller;

import com.example.quiz.dto.Answer;
import com.example.quiz.dto.PrbUserAnswer;
import com.example.quiz.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/answer")
@RestController
public class AnswerController {
    @Resource(name = "answerService")
    private final AnswerService answerService;

    @PostMapping
    private List<Answer> insertAnswer(@RequestBody PrbUserAnswer prbUserAnswer){
        return answerService.insertAnswer(prbUserAnswer);
    }
}
