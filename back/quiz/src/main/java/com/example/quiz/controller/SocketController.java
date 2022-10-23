package com.example.quiz.controller;

import com.example.quiz.domain.user.User;
import com.example.quiz.dto.*;
import com.example.quiz.service.AnswerService;
import com.example.quiz.service.RoomEntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.simp.SimpMessageSendingOperations;

import javax.annotation.Resource;

@RequiredArgsConstructor
@Controller
public class SocketController {
    private final SimpMessageSendingOperations messagingTemplate;

    @Resource(name ="roomEntryService")
    private final RoomEntryService roomEntryService;

    @Resource(name = "answerService")
    private final AnswerService answerService;

    @MessageMapping("/enter")
    private void enter(Enter enter){
        System.out.println("Enter: "+enter);
        if(!enter.getIsOwner()){
            User user= User.builder().userNo(enter.getUserNo()).build();
            Room room= Room.builder().roomNo(enter.getRoomNo()).build();
            RoomEntry roomEntry=RoomEntry.builder().room(room).user(user).isOwner(false).build();
            roomEntryService.createRoomEntry(roomEntry);
        }
        messagingTemplate.convertAndSend("/sub/enter/"+ enter.getRoomNo(),roomEntryService.findAllByRoomNo(enter.getRoomNo()));
    }

    @MessageMapping("/play")
    private void enter(Long roomNo){
        System.out.println("Play: "+roomNo);
        messagingTemplate.convertAndSend("/sub/play/"+ roomNo,"");
    }

    @MessageMapping("/answer")
    private void answer(PrbUserAnswer prbUserAnswer){
        answerService.insertAnswer(prbUserAnswer);
        messagingTemplate.convertAndSend("/sub/answer/"+ prbUserAnswer.getRoomNo(),answerService.getRank(prbUserAnswer.getRoomNo()));
    }
}
