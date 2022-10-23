package com.example.quiz.dto;

import com.example.quiz.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@IdClass(RoomEntryId.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomEntry {
    @Id
    @ManyToOne
    @JoinColumn(name = "room_no")
    private Room room;
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private long userScore;
    private boolean isOwner;
}
