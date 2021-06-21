package com.example.quiz.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Entity
@Data
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long roomNo;
    private String roomName;
    private long owner_no;
    private String endCode;
    private int capacity;
    private boolean isPrivate;
    @CreationTimestamp
    private LocalDateTime regTime;
}
