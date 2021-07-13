package com.example.quiz.repository;

import com.example.quiz.dto.Answer;
import com.example.quiz.dto.AnswerId;
import com.example.quiz.dto.RankResult;
import com.example.quiz.dto.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends CrudRepository<Answer, AnswerId> {
    List<Answer> findAllByRoomNoAndUser(long roomNo, User user);

    @Modifying
    @Query(value = "select a.user_no as userNo,a.user_id,sum(if( a.is_answer=1, a.score, 0)),count(if(a.is_answer=1,a.is_answer,null)) as total from answer a where a.room_no=? group by user_no order by total desc",nativeQuery=true)
    List<Object[]> getRank(Long roomNo);
}
