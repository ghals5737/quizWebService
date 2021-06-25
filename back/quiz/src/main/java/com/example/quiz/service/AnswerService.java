package com.example.quiz.service;

import com.example.quiz.dto.Answer;
import com.example.quiz.dto.PrbUserAnswer;

import java.util.List;

public interface AnswerService {
    List<Answer> insertAnswer(PrbUserAnswer prbUserAnswer);
}
