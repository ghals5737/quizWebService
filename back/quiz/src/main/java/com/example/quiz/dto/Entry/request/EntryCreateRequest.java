package com.example.quiz.dto.Entry.request;

import com.example.quiz.domain.entry.Entry;
import com.example.quiz.domain.room.Room;
import com.example.quiz.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class EntryCreateRequest {
    private long userNo;
    private long roomNo;

    public Entry toEntry(){
        return Entry.builder()
                .room(Room.builder().roomNo(roomNo).build())
                .user(User.builder().userNo(userNo).build())
                .score(0)
                .regTime(LocalDateTime.now())
                .build();
    }
}
