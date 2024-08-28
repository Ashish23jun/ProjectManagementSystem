package com.ashley.projectmanagementsystem.Repository;

import com.ashley.projectmanagementsystem.Model.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvitationRepository extends JpaRepository<Invitation, Long> {

    Invitation findByToken(String token);

    Invitation findByEmail(String useEmail);

}
