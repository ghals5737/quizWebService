package com.example.quiz.service;

import com.example.quiz.dto.Room;
import com.example.quiz.dto.RoomEntry;
import com.example.quiz.dto.User;
import com.example.quiz.repository.RoomEntryRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service("roomEntryService")
public class RoomEntryServiceImpl implements  RoomEntryService{
    @Autowired
    private final RoomEntryRepository roomEntryRepository;

    @Override
    public void createRoomEntry(Room room) {
        RoomEntry roomEntry=new RoomEntry();
        User user=new User();
        user.setUserNo(room.getOwner_no());
        roomEntry.setRoom(room);
        roomEntry.setUser(user);
        roomEntry.setOwner(true);
        roomEntryRepository.save(roomEntry);
    }

    @Override
    public RoomEntry createRoomEntry(RoomEntry roomEntry) {
        return roomEntryRepository.save(roomEntry);
    }
}
