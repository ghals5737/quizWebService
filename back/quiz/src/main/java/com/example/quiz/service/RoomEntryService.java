package com.example.quiz.service;

import com.example.quiz.dto.Room;
import com.example.quiz.dto.RoomEntry;
import com.example.quiz.dto.RoomEntryId;
import com.example.quiz.domain.user.User;

import java.util.List;

public interface RoomEntryService {
    void createRoomEntry(Room room);
    RoomEntry createRoomEntry(RoomEntry roomEntry);
    void deleteRoomEntry(RoomEntryId roomEntryId);
    List<User> findAllByRoomNo(long roomNo);
    List<Room> findAllByUserNo(long userNo);
    void deleteRoomEntriesByRoom(Long roomNo);
}
