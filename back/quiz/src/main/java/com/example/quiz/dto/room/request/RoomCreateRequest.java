package com.example.quiz.dto.room.request;

import com.example.quiz.domain.room.Room;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class RoomCreateRequest {
    private String roomName;
    private long hostNo;
    private int capacity;

    public Room toRoom(String joinCode){
        return Room.builder()
                .roomName(roomName)
                .hostNo(hostNo)
                .capacity(capacity)
                .joinCode(joinCode)
                .regTime(LocalDateTime.now())
                .build();
    }
}
