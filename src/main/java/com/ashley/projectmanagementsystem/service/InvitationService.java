package com.ashley.projectmanagementsystem.service;

import com.ashley.projectmanagementsystem.Model.Invitation;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.MimeMessageHelper;

public interface InvitationService {
    public void sendInvitation(String email, Long projectId) throws MessagingException;

    public Invitation acceptInvitation(String token, Long userId) throws Exception;

    public String getTokenByUserMail(String email) ;

    void deleteToken(String email) ;
}
