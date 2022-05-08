package com.example.quiz.service;

import com.example.quiz.repository.AnswerRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AnswerTest {

    @Mock
    AnswerRepository answerRepository;

    @InjectMocks
    AnswerService answerService;
}
