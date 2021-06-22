package com.example.quiz.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "quiz_no")
    private Quiz quiz;
    @JsonManagedReference
    @OneToMany(mappedBy = "problem")
    private List<Example> exampleList=new ArrayList<>();
}
