package com.example.quiz.controller;

import com.example.quiz.dto.SocketMsg;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.simp.SimpMessageSendingOperations;

@RequiredArgsConstructor
@Controller
public class SocketController {
    private final SimpMessageSendingOperations messagingTemplate;

    @MessageMapping("/chat/message")
    private void message(SocketMsg socketMsg){
        messagingTemplate.convertAndSend("/sub/chat",socketMsg.getMsg());
    }
}
