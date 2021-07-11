package com.example.quiz.service;

import com.example.quiz.dto.Quiz;
import com.example.quiz.dto.Room;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RoomService {
    Room createRoom(Room room);
    Room searchRoomByRoomNo(Long roomNo);
    void deleteRoom(Long roomNo);
    Long getTotal();
    List<Room> findAllByPageable(Pageable pageable);
}
