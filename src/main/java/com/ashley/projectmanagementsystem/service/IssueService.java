package com.ashley.projectmanagementsystem.service;

import com.ashley.projectmanagementsystem.Model.Issue;
import com.ashley.projectmanagementsystem.Model.User;
import com.ashley.projectmanagementsystem.request.IssueRequest;

import java.util.List;
import java.util.Optional;

public interface IssueService {
    Optional<Issue> getIssueById(Long issueId) throws Exception;

    List<Issue> getIssuesByProjectId(Long projectId) throws Exception;

    void deleteIssue(Long issueId, Long userId) throws Exception;

    Issue addUserToIssue(Long issueId, Long userId) throws Exception;

    Issue updateStatus(Long issueId, String status) throws Exception;

    Issue createIssue(IssueRequest issue, User tokenUser) throws Exception;

}
