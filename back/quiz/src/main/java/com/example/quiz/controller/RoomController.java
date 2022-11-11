package com.example.quiz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/room")
@RestController
public class RoomController {
//    @Resource(name = "roomService")
//    private final RoomService roomService;
//
//    @Resource(name = "roomEntryService")
//    private final RoomEntryService roomEntryService;
//
//    @Resource(name = "roomQuizService")
//    private final RoomQuizService roomQuizService;
//
//    @GetMapping()
//    private List<Room> getRoomListByUser(Long userNo){
//        return roomEntryService.findAllByUserNo(userNo);
//    }
//
//    @PostMapping()
//    private Room createRoom(@RequestBody Room room){
//        try{
//            Room result=roomService.createRoom(room);
//            roomEntryService.createRoomEntry(room);
//            return result;
//        }catch (Exception e){
//            throw new RestException(HttpStatus.BAD_REQUEST,"invalid userNo");
//        }
//    }
//    @GetMapping("search")
//    private Room searchRoomByRoomNo(Long roomNo){
//        return roomService.searchRoomByRoomNo(roomNo);
//    }
//
//    @PutMapping("/quizs")
//    private void addRoomQuiz(@RequestBody RoomQuizNo roomQuizNo){
//        roomQuizService.createRoomQuiz(roomQuizNo);
//    }
//
//    @GetMapping("/allroom")
//    private List<Room> findAllByPageable(int page, int size){
//        return roomService.findAllByPageable(PageRequest.of(page,size));
//    }
//
//    @GetMapping("/total")
//    private Long getTotal(){
//        return roomService.getTotal();
//    }
//
//    @DeleteMapping()
//    private void deleteRoom(long roomNo){
//        try{
//            roomEntryService.deleteRoomEntriesByRoom(roomNo);
//            roomQuizService.deleteRoomQuizsByRoom(roomNo);
//            roomService.deleteRoom(roomNo);
//        }catch (Exception e){
//            throw new RestException(HttpStatus.SERVICE_UNAVAILABLE,e.getMessage());
//        }
//    }
}
