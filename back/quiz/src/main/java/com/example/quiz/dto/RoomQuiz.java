package com.example.quiz.dto;

import lombok.Data;

import javax.persistence.*;

@Entity
@IdClass(RoomQuizId.class)
@Data
public class RoomQuiz {
    @Id
    @ManyToOne
    @JoinColumn(name = "room_no")
    private Room room;
    @Id
    @ManyToOne
    @JoinColumn(name = "quiz_no")
    private Quiz quiz;
    private int quizOrder;
}
