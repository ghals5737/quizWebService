package com.example.quiz.service;

import com.example.quiz.dto.Room;
import com.example.quiz.dto.RoomEntry;

public interface RoomEntryService {
    void createRoomEntry(Room room);
    RoomEntry createRoomEntry(RoomEntry roomEntry);
}
