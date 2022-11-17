package com.example.quiz.service;

import com.example.quiz.dto.Answer;
import com.example.quiz.dto.Example;
import com.example.quiz.dto.Problem;
import com.example.quiz.repository.AnswerRepository;
import com.example.quiz.repository.ExampleRepository;
import com.example.quiz.repository.ProblemRepository;
import com.example.quiz.repository.QuizRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;

@AllArgsConstructor
@Service("quizService")
public class QuizServiceImpl implements QuizService{
//    @Autowired
//    private final QuizRepository quizRepository;
//
//    @Autowired
//    private final ProblemRepository problemRepository;
//
//    @Autowired
//    private final ExampleRepository exampleRepository;
//
//    @Autowired
//    private final AnswerRepository answerRepository;
//
//    @Override
//    public Quiz createQuiz(Quiz quiz) {
//        Quiz result=quizRepository.save(quiz);
//        Quiz fkQuiz=Quiz.builder().quizNo(result.getQuizNo()).build();
//        for(Problem prb:quiz.getProblemList()){
//            prb.setQuiz(fkQuiz);
//            Problem savePrb=problemRepository.save(prb);
//            System.out.println("prbNo"+savePrb.getPrbNo());
//            System.out.println("prbTitle"+savePrb.getTitle());
//            Problem fkPrb=Problem.builder().prbNo(savePrb.getPrbNo()).build();
//            for(Example exam:savePrb.getExampleList()){
//                exam.setProblem(fkPrb);
//                exampleRepository.save(exam);
//            }
//        }
//        return result;
//    }
//
//    @Override
//    public Quiz getQuizByNo(Long quizNo) {
//        return quizRepository.findQuizByQuizNo(quizNo);
//    }
//
//    @Override
//    public void deleteQuiz(long quizNo) {
//        Quiz quiz=quizRepository.findQuizByQuizNo(quizNo);
//        quiz.getProblemList().forEach(el->{
//            List<Answer> answerList=answerRepository.findAllByProblem(el);
//            answerList.forEach(answerRepository::delete);
//            el.getExampleList().forEach(exampleRepository::delete);
//            problemRepository.delete(el);
//        });
//        quizRepository.delete(quiz);
//
//    }
//
//    @Override
//    public List<Quiz> findAllByPageable(Pageable pageable) {
//        return quizRepository.findAll(pageable).getContent();
//    }
//
//    @Override
//    public Long getTotal() {
//        return quizRepository.count();
//    }
}
