package com.learnsphere.service;

import com.learnsphere.model.ChatMessage;
import com.learnsphere.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ChatService {

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public ChatMessage saveAndBroadcastMessage(ChatMessage message) {
        message.setTimestamp(LocalDateTime.now());
        ChatMessage savedMessage = chatMessageRepository.save(message);
        messagingTemplate.convertAndSend("/topic/group/" + message.getGroupId(), savedMessage);
        return savedMessage;
    }
}
