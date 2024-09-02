package com.ashley.projectmanagementsystem.controller;

import com.ashley.projectmanagementsystem.Model.Chat;
import com.ashley.projectmanagementsystem.Model.Message;
import com.ashley.projectmanagementsystem.Model.User;
import com.ashley.projectmanagementsystem.request.CreateMessageRequest;
import com.ashley.projectmanagementsystem.service.MessageService;
import com.ashley.projectmanagementsystem.service.UserService;
import com.ashley.projectmanagementsystem.service.ProjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    public MessageController(MessageService messageService, UserService userService, ProjectService projectService) {
        this.messageService = messageService;
        this.userService = userService;
        this.projectService = projectService;
    }

    @PostMapping("/send")
    public ResponseEntity<Message> sendMessage(
            @RequestBody CreateMessageRequest req) throws Exception {
        User sender = userService.findUserById(req.getSenderId());
        if (sender == null) {
            throw new Exception("User not found with Id " + req.getSenderId());
        }

        Chat chat = projectService.getProjectById(req.getProjectId()).getChat();
        if (chat == null) {
            throw new Exception("Chat not found for project Id " + req.getProjectId());
        }

        Message createdMessage = messageService.sendMessage(
                req.getSenderId(),
                req.getProjectId(),
                req.getContent());

        return new ResponseEntity<>(createdMessage, HttpStatus.CREATED);
    }

    @GetMapping("/chat/{projectId}")
    public ResponseEntity<List<Message>> getMessagesByProjectId(
            @PathVariable Long projectId) throws Exception {
        List<Message> messages = messageService.getMessageByProjectId(projectId);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }
}
