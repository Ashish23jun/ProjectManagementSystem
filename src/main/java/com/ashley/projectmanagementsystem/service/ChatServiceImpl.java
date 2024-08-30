package com.ashley.projectmanagementsystem.service;

import com.ashley.projectmanagementsystem.Model.Chat;
import com.ashley.projectmanagementsystem.Repository.ChatRepository;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService {

    private ChatRepository chatRepository;

    @Override
    public Chat createChat(Chat chat) {
        return chatRepository.save(chat);

    }
}
