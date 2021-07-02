package com.example.quiz.service;

import com.example.quiz.dto.Room;
import com.example.quiz.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service("roomService")
public class RoomServiceImpl implements  RoomService{
    @Autowired
    private final RoomRepository roomRepository;

    @Override
    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public void deleteRoom(Long roomNo) {
        Room room=roomRepository.findRoomByRoomNo(roomNo);
        roomRepository.delete(room);
    }

    @Override
    public Long getTotal() {
        return roomRepository.count();
    }

    @Override
    public List<Room> findAllByPageable(Pageable pageable) {
        return roomRepository.findAll(pageable).getContent();
    }
}
