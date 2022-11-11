package com.example.quiz.controller;

import com.example.quiz.dto.RoomEntry;
import com.example.quiz.dto.RoomEntryId;
import com.example.quiz.service.RoomEntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RequiredArgsConstructor
@RequestMapping("/api/room-entry")
@RestController
public class RoomEntryController {
//    @Resource(name = "roomEntryService")
//    private final RoomEntryService roomEntryService;
//
//    @PostMapping()
//    private RoomEntry EnterRoom(@RequestBody RoomEntry roomEntry){
//        return roomEntryService.createRoomEntry(roomEntry);
//    }
//
//    @DeleteMapping()
//    private void deleteRoomEntry(@RequestBody RoomEntryId roomEntryId){
//        roomEntryService.deleteRoomEntry(roomEntryId);
//    }
}
