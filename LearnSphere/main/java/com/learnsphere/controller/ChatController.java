package com.learnsphere.controller;

import com.learnsphere.model.ChatMessage;
import com.learnsphere.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @Autowired
    private ChatService chatService;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/group/{groupId}")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatService.saveAndBroadcastMessage(chatMessage);
    }
}
