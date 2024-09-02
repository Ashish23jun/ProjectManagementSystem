package com.ashley.projectmanagementsystem.service;

import java.util.List;

import com.ashley.projectmanagementsystem.Model.Message;

public interface MessageService {

    Message sendMessage(Long senderId, Long chatId, String content) throws Exception;

    // void deleteMessage(Long messageId, Long userId) throws Exception;

    List<Message> getMessageByProjectId(Long projectId) throws Exception;
}
