package com.example.quiz.dto;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
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
