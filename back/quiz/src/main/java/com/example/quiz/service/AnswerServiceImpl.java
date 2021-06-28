package com.example.quiz.service;

import com.example.quiz.dto.*;
import com.example.quiz.repository.AnswerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service("answerService")
public class AnswerServiceImpl implements AnswerService{
    @Autowired
    private final AnswerRepository answerRepository;

    @Override
    public List<Answer> insertAnswer(PrbUserAnswer prbUserAnswer) {
        List<Answer>answerList=new ArrayList<>();
        long prbNo= prbUserAnswer.getPrbNo();
        for(UserAnswer el:prbUserAnswer.getUserAnswerList()){
            Answer answer=Answer.builder()
                    .problem(Problem.builder().prbNo(prbNo).build())
                    .user(User.builder().userNo(el.getUserNo()).build())
                    .answer(el.getAnswer())
                    .build();
            answerList.add(answerRepository.save(answer));
        }
        return answerList;
    }
}
