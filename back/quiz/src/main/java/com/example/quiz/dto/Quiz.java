package com.example.quiz.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long quizNo;
    private long userNo;
    private String quizName;
    @CreationTimestamp
    private LocalDateTime regTime;
    @JsonManagedReference
    @JsonProperty(value = "problemList")
    @OneToMany(mappedBy = "quiz", fetch = FetchType.LAZY)
    private List<Problem> problemList=new ArrayList<>();
}
