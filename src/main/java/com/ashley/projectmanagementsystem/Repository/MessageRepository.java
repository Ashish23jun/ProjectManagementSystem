package com.ashley.projectmanagementsystem.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashley.projectmanagementsystem.Model.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByChatIdOrdereByCreatedAtAsc(Long chatId);
}
