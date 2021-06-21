package com.example.quiz.dto;

import lombok.Data;

import javax.persistence.*;

@Entity
@IdClass(RoomEntryId.class)
@Data
public class RoomEntry {
    @Id
    @ManyToOne
    @JoinColumn(name = "room_no")
    private Room room;
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private boolean isOwner;
}
