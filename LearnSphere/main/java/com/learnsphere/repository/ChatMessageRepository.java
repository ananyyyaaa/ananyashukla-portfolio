package com.learnsphere.repository;

import com.learnsphere.model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findByGroupIdOrderByTimestampAsc(Long groupId);
}
