package com.example.quiz.controller;

import com.example.quiz.dto.Room;
import com.example.quiz.dto.RoomEntry;
import com.example.quiz.dto.RoomQuizNo;
import com.example.quiz.exception.RestException;
import com.example.quiz.service.RoomEntryService;
import com.example.quiz.service.RoomQuizService;
import com.example.quiz.service.RoomService;
import com.example.quiz.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/room")
@RestController
public class RoomController {
    @Resource(name = "roomService")
    private final RoomService roomService;

    @Resource(name = "roomEntryService")
    private final RoomEntryService roomEntryService;

    @Resource(name = "roomQuizService")
    private final RoomQuizService roomQuizService;

    @PostMapping()
    private Room createRoom(@RequestBody Room room){
        try{
            Room result=roomService.createRoom(room);
            roomEntryService.createRoomEntry(room);
            return result;
        }catch (Exception e){
            throw new RestException(HttpStatus.BAD_REQUEST,"invalid userNo");
        }
    }

    @PostMapping("/enter")
    private RoomEntry EnterRoom(@RequestBody RoomEntry roomEntry){
        return roomEntryService.createRoomEntry(roomEntry);
    }

    @PutMapping("/quizs")
    private void addRoomQuiz(@RequestBody RoomQuizNo roomQuizNo){
        roomQuizService.createRoomQuiz(roomQuizNo);
    }
}
