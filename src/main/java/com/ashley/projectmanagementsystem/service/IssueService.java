package com.ashley.projectmanagementsystem.service;

import com.ashley.projectmanagementsystem.Model.Issue;
import com.ashley.projectmanagementsystem.request.IssueRequest;

import java.util.List;
import java.util.Optional;

public interface IssueService {
    Optional<Issue> getIssueById(Long issueId) throws Exception;

    List<Issue> getIssuesByProjectId(Long projectId) throws Exception;

    Issue createIssue(IssueRequest issue, Long userId) throws Exception;

    List<Issue> getIssuesByAssigneeId(Long assigneeId) throws Exception;

    //List<Issue> searchIssues(String title, String status, String ) throws Exception;



    Issue updateIssue(Issue issue) throws Exception;

    void deleteIssue(Long issueId) throws Exception;

    void deleteIssuesByProjectId(Long projectId) throws Exception;

    void deleteIssuesByAssigneeId(Long assigneeId) throws Exception;


}
