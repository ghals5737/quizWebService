package com.example.quiz.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(exclude = {"problem"})
public class Example {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long exampleNO;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "prb_no")
    private Problem problem;
    private int examNo;
    private String des;
}
