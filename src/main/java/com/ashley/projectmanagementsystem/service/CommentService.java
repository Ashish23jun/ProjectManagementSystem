package com.ashley.projectmanagementsystem.service;

import java.util.List;

import com.ashley.projectmanagementsystem.Model.Comment;

public interface CommentService {
    Comment createComment(Long issueId, Long userId, String comment) throws Exception;

    void deleteComment(Long commentId, Long userId) throws Exception;

    List<Comment> findCommentByissueId(Long issueId);
}
