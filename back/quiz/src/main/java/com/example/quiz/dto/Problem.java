package com.example.quiz.dto;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Problem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long prbNo;
    private int prbOder;
    private String imgUrl;
    private String title;
    private String answer;
    private int score;
    private int quizType;
    @ManyToOne
    @JoinColumn(name = "quiz_no")
    private Quiz quiz;
}
