package com.ashley.projectmanagementsystem.Model;

import java.time.LocalDate;
import java.util.ArrayList; // Add this import statement
import java.util.List;

import lombok.NoArgsConstructor;

import lombok.AllArgsConstructor;

import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IssueDTO {
    private Long id;
    private String title;
    private String description;
    private String status;
    private Long projectId;
    private String priority;
    private LocalDate dueDate;
    private List<String> tags = new ArrayList<>();
    private Project project;
    private User assignee;
}
