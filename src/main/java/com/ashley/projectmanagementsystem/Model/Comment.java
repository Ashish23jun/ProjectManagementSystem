package com.ashley.projectmanagementsystem.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime; // Corrected to LocalDateTime

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String content;
    private LocalDateTime createdDateTime; // Corrected to LocalDateTime

    @ManyToOne
    private User user;

    @ManyToOne
    private Issue issue;
}
