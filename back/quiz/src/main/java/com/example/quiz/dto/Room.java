package com.example.quiz.dto;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long roomNo;
    private String roomName;
    private int endCode;
    private int capacity;
    private boolean isPrivate;
    @CreationTimestamp
    private LocalDateTime regTime;
}
