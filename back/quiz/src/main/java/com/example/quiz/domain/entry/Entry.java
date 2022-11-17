package com.example.quiz.domain.entry;

import com.example.quiz.domain.room.Room;
import com.example.quiz.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@IdClass(EntryId.class)
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
    @JoinColumn(name = "user_no")
    private User user;
    private long score;
    private LocalDateTime regTime;
}
