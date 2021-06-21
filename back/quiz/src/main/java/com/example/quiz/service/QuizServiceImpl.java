package com.example.quiz.service;

import com.example.quiz.dto.Quiz;
import com.example.quiz.repository.ExampleRepository;
import com.example.quiz.repository.ProblemRepository;
import com.example.quiz.repository.QuizRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service("quizService")
public class QuizServiceImpl implements QuizService{
    @Autowired
    private final QuizRepository quizRepository;

    @Autowired
    private final ProblemRepository problemRepository;

    @Autowired
    private final ExampleRepository exampleRepository;

    @Override
    public Quiz createQuiz(Quiz quiz) {
        quiz.getProblemList().forEach(el->{
            problemRepository.save(el);
            el.getExampleList().forEach(exampleRepository::save);
        });
        return quizRepository.save(quiz);
    }

    @Override
    public List<Quiz> finfByRoomNo(long roomNo) {

        return null;
    }
}
