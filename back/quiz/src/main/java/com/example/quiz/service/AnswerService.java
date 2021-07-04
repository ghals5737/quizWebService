package com.example.quiz.service;

import com.example.quiz.dto.Answer;
import com.example.quiz.dto.AnswerResult;
import com.example.quiz.dto.PrbUserAnswer;

import java.util.List;

public interface AnswerService {
    List<Answer> insertAnswer(PrbUserAnswer prbUserAnswer);
    List<AnswerResult> getAnswerByRoom(Long roomNO, Long userNo);
}
