package com.example.quiz.controller;

import com.example.quiz.dto.Quiz;
import com.example.quiz.dto.Room;
import com.example.quiz.dto.RoomEntry;
import com.example.quiz.dto.RoomQuizNo;
import com.example.quiz.exception.RestException;
import com.example.quiz.service.RoomEntryService;
import com.example.quiz.service.RoomQuizService;
import com.example.quiz.service.RoomService;
import com.example.quiz.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
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

    @GetMapping()
    private List<Room> getRoomListByUser(Long userNo){
        return roomEntryService.findAllByUserNo(userNo);
    }

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

    @PutMapping("/quizs")
    private void addRoomQuiz(@RequestBody RoomQuizNo roomQuizNo){
        roomQuizService.createRoomQuiz(roomQuizNo);
    }

    @GetMapping("/allroom")
    private List<Room> findAllByPageable(int page, int size){
        return roomService.findAllByPageable(PageRequest.of(page,size));
    }

    @GetMapping("/total")
    private Long getTotal(){
        return roomService.getTotal();
    }

    @DeleteMapping()
    private void deleteRoom(long roomNo){
        try{
            roomEntryService.deleteRoomEntriesByRoom(roomNo);
            roomQuizService.deleteRoomQuizsByRoom(roomNo);
            roomService.deleteRoom(roomNo);
        }catch (Exception e){
            throw new RestException(HttpStatus.SERVICE_UNAVAILABLE,e.getMessage());
        }
    }
}
