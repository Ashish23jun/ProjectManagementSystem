package com.ashley.projectmanagementsystem.Repository;

import com.ashley.projectmanagementsystem.Model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat,Long> {

}
