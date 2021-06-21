package com.example.quiz.controller;

import com.example.quiz.dto.Room;
import com.example.quiz.dto.RoomEntry;
import com.example.quiz.exception.RestException;
import com.example.quiz.service.RoomEntryService;
import com.example.quiz.service.RoomService;
import com.example.quiz.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequiredArgsConstructor
@RequestMapping("/api/room")
@RestController
public class RoomController {
    @Resource(name = "roomService")
    private final RoomService roomService;

    @Resource(name = "roomEntryService")
    private final RoomEntryService roomEntryService;

    @PostMapping()
    private Room createRoom(@RequestBody Room room){
        try{
            roomEntryService.createRoomEntry(room);
            return roomService.createRoom(room);
        }catch (Exception e){
            throw new RestException(HttpStatus.BAD_REQUEST,"invalid userNo");
        }
    }

    @PostMapping("/enter")
    private RoomEntry EnterRoom(@RequestBody RoomEntry roomEntry){
        return roomEntryService.createRoomEntry(roomEntry);
    }
}
