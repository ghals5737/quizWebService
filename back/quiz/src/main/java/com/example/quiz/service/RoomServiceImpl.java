package com.example.quiz.service;

import com.example.quiz.dto.Room;
import com.example.quiz.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service("roomService")
public class RoomServiceImpl implements  RoomService{
    @Autowired
    private final RoomRepository roomRepository;

    @Override
    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }
}
