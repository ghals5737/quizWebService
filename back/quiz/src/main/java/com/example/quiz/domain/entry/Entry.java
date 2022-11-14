package com.example.quiz.domain.entry;

import com.example.quiz.domain.room.Room;
import com.example.quiz.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Entry {
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
