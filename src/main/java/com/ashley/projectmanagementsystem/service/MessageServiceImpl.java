package com.ashley.projectmanagementsystem.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashley.projectmanagementsystem.Model.Chat;
import com.ashley.projectmanagementsystem.Model.Message;
import com.ashley.projectmanagementsystem.Model.User; // Add this import statement
import com.ashley.projectmanagementsystem.Repository.MessageRepository;
import com.ashley.projectmanagementsystem.Repository.UserRepository;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectService projectService;

    @Override
    public Message sendMessage(Long senderId, Long projectId, String content) throws Exception {
        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new Exception("User not found with id " + senderId));

        Chat chat = projectService.getProjectById(projectId).getChat();

        Message message = new Message();
        message.setChat(chat);
        message.setContent(content);
        message.setSender(sender);
        message.setCreatedAt(LocalDateTime.now());
        Message savedmessage = messageRepository.save(message);
        chat.getMessages().add(savedmessage);
        return messageRepository.save(message);
    }

    @Override
    public List<Message> getMessageByProjectId(Long projectId) throws Exception {
        Chat chat = projectService.getProjectById(projectId).getChat();
        List<Message> findByChatIdOrdereByCreatedAtAsc = messageRepository
                .findByChatIdOrdereByCreatedAtAsc(chat.getId());
        return findByChatIdOrdereByCreatedAtAsc;
    }

}
