package com.example.quiz.service;

import com.example.quiz.dto.Room;
import com.example.quiz.dto.RoomEntry;
import com.example.quiz.dto.RoomEntryId;
import com.example.quiz.dto.User;
import com.example.quiz.repository.RoomEntryRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service("roomEntryService")
public class RoomEntryServiceImpl implements  RoomEntryService{
    @Autowired
    private final RoomEntryRepository roomEntryRepository;

    @Override
    public void createRoomEntry(Room room) {
        User user=User.builder().userNo(room.getOwner_no()).build();
        RoomEntry roomEntry=RoomEntry.builder().room(room).user(user).isOwner(true).build();
        roomEntryRepository.save(roomEntry);
    }

    @Override
    public RoomEntry createRoomEntry(RoomEntry roomEntry) {
        return roomEntryRepository.save(roomEntry);
    }

    @Override
    public void deleteRoomEntry(RoomEntryId roomEntryId) {
        roomEntryRepository.deleteById(roomEntryId);
    }

    @Override
    public List<User> findAllByRoomNo(long roomNo) {
        Room room=Room.builder().roomNo(roomNo).build();
        List<RoomEntry> roomEntryList=roomEntryRepository.findRoomEntryByRoomOrderByUserScore(room);
        List<User> userList= new ArrayList<>();
        roomEntryList.forEach(el->userList.add(el.getUser()));
        return userList;
    }

    @Override
    public List<Room> findAllByUserNo(long userNo) {
        User user=User.builder().userNo(userNo).build();
        List<RoomEntry> roomEntryList=roomEntryRepository.findRoomEntryByUserOrderByRoom(user);
        List<Room> roomList=new ArrayList<>();
        roomEntryList.forEach(el->roomList.add(el.getRoom()));
        return roomList;
    }

    @Override
    public void deleteRoomEntriesByRoom(Long roomNo){
        Room room=Room.builder().roomNo(roomNo).build();
        roomEntryRepository.deleteRoomEntriesByRoom(room);
    }
}
