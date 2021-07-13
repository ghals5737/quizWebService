package com.example.quiz.controller;

import com.example.quiz.dto.Answer;
import com.example.quiz.dto.AnswerResult;
import com.example.quiz.dto.PrbUserAnswer;
import com.example.quiz.dto.RankResult;
import com.example.quiz.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/answer")
@RestController
public class AnswerController {
    @Resource(name = "answerService")
    private final AnswerService answerService;

    @PostMapping()
    private List<Answer> insertAnswer(@RequestBody PrbUserAnswer prbUserAnswer){
        return answerService.insertAnswer(prbUserAnswer);
    }

    @GetMapping()
    private List<AnswerResult> getAnswerByRoom(Long roomNo, Long userNo){
        return answerService.getAnswerByRoom(roomNo,userNo);
    }

    @GetMapping("/rank")
    private List<RankResult> getRank(Long roomNo){
        System.out.println("Answer Rank Controller");
        return answerService.getRank(roomNo);
    }
}
