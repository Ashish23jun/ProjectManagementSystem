package com.ashley.projectmanagementsystem.service;

import java.time.LocalDateTime; // Corrected import for LocalDateTime
import java.util.Optional;
import java.util.List; // Corrected import for List

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashley.projectmanagementsystem.Model.Comment;
import com.ashley.projectmanagementsystem.Model.Issue;
import com.ashley.projectmanagementsystem.Model.User;
import com.ashley.projectmanagementsystem.Repository.CommentRepository;
import com.ashley.projectmanagementsystem.Repository.IssueRepository;
import com.ashley.projectmanagementsystem.Repository.UserRepository;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Comment createComment(Long issueId, Long userId, String content) throws Exception {
        Optional<Issue> issueOptional = issueRepository.findById(issueId);
        Optional<User> userOptional = userRepository.findById(userId);

        if (issueOptional.isEmpty()) {
            throw new Exception("No issue found with issueId " + issueId);
        }
        if (userOptional.isEmpty()) {
            throw new Exception("No user found with userId " + userId);
        }

        Issue issue = issueOptional.get();
        User user = userOptional.get();

        Comment comment = new Comment();
        comment.setIssue(issue);
        comment.setUser(user);
        comment.setCreatedDateTime(LocalDateTime.now());
        comment.setContent(content);

        Comment savedComment = commentRepository.save(comment);

        issue.getComments().add(savedComment);

        return savedComment;

    }

    @SuppressWarnings("unlikely-arg-type")
    @Override
    public void deleteComment(Long commentId, Long userId) throws Exception {
        Optional<Comment> optionalComment = commentRepository.findById(userId);
        Optional<User> userOptional = userRepository.findById(userId);

        if (optionalComment.isEmpty()) {
            throw new Exception("comment not found with commentId " + commentId);
        }

        if (userOptional.isEmpty()) {
            throw new Exception("user not found with userId " + userId);

        }
        Comment comment = optionalComment.get();
        User user = userOptional.get();

        if (comment.getUser().equals(comment)) {
            commentRepository.delete(comment);
        } else {
            throw new Exception("User does not have permission to delete this comment");
        }
    }

    @Override
    public List<Comment> findCommentByissueId(Long issueId) {

        return commentRepository.findByIssueId(issueId);

    }
}
