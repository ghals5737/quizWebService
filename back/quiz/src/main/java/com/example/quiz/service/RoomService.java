package com.example.quiz.service;

import com.example.quiz.dto.room.request.RoomCreateRequest;
import com.example.quiz.repository.room.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;

    public void createRoom(RoomCreateRequest request) {
        roomRepository.save(request.toRoom("a"));
    }
}
