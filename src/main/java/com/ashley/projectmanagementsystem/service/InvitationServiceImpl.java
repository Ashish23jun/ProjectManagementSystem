package com.ashley.projectmanagementsystem.service;

import com.ashley.projectmanagementsystem.Model.Invitation;
import com.ashley.projectmanagementsystem.Repository.InvitationRepository;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class InvitationServiceImpl implements InvitationService {

    @Autowired
    private final InvitationRepository invitationRepository;

    @Autowired
    private EmailService emailService;

    public InvitationServiceImpl(InvitationRepository invitationRepository) {
        this.invitationRepository = invitationRepository;
    }

    @Override
    public void sendInvitation(String email, Long projectId) throws MessagingException {
        String invitationToken = UUID.randomUUID().toString();

        Invitation invitation = new Invitation();
        invitation.setEmail(email);
        invitation.setProjectId(projectId);
        invitation.setToken(invitationToken);

        invitationRepository.save(invitation);
        String invitationLink = "http://localhost:5173/accept_invitation?token=" + invitationToken;
        emailService.sendEmailWithToken(email, invitationLink);
    }

    @Override
    public Invitation acceptInvitation(String token, Long userId) throws Exception {
        Invitation invitation = invitationRepository.findByToken(token);
        if(invitation == null) {
            throw new Exception("Invalid token");
        }
        return invitation;
    }

    @Override
    public String getTokenByUserMail(String email) {

        Invitation invitation = invitationRepository.findByEmail(email);
        return invitation.getToken();

    }

    @Override
    public void deleteToken(String email) {

        Invitation invitation = invitationRepository.findByToken(email);
        invitationRepository.delete(invitation);

    }
}
