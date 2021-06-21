package com.example.quiz.dto;

import lombok.Data;

import javax.persistence.*;

@Entity
@IdClass(AnswerId.class)
@Data
public class Answer {
    @Id
    @ManyToOne
    @JoinColumn(name = "prb_no")
    private Problem problem;
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String  answer;
    private boolean isAnswer;
}
