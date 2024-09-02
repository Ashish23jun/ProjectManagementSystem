package com.ashley.projectmanagementsystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashley.projectmanagementsystem.Model.Comment;

import java.util.List; // Add this import statement

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByIssueId(Long issueId);

}
