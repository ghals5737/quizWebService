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
        long userNo= prbUserAnswer.getUserNo();
        long roomNo= prbUserAnswer.getRoomNo();
        for(UserAnswer el:prbUserAnswer.getUserAnswerList()){
            boolean isAnswer=false;
            if(el.getAnswer().equals(el.getSubmitAnswer().get(0)))isAnswer=true;
            Answer answer=Answer.builder()
                    .problem(Problem.builder().prbNo(el.getPrbNo()).build())
                    .user(User.builder().userNo(userNo).build())
                    .roomNo(roomNo)
                    .score(el.getScore())
                    .answer(el.getSubmitAnswer().get(0))
                    .isAnswer(isAnswer)
                    .build();
            answerList.add(answerRepository.save(answer));
        }
        return answerList;
    }

    @Override
    public List<AnswerResult> getAnswerByRoom(Long roomNo, Long userNo) {
        List<Answer> answers=answerRepository.findAllByRoomNoAndUser(roomNo,User.builder().userNo(userNo).build());
        List<AnswerResult>res=new ArrayList<>();
        answers.forEach(el->{
            AnswerResult answerResult=AnswerResult.builder().answer(el.getAnswer()).isAnswer(el.getIsAnswer()).score(el.getScore()).build();
            res.add(answerResult);
        });
        return res;
    }
}
