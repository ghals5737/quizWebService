package com.example.quiz.dto;

import com.example.quiz.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@IdClass(AnswerId.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Answer {
    @Id
    @ManyToOne
    @JoinColumn(name = "prb_no")
    private Problem problem;
    @Id
    @ManyToOne
    @JoinColumn(name = "user_no")
    private User user;
    private String  answer;
    private String userId;
    private Long roomNo;
    private int score;
    private Boolean isAnswer;
}
