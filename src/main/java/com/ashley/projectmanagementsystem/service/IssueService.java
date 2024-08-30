package com.ashley.projectmanagementsystem.service;

import com.ashley.projectmanagementsystem.Model.Issue;

import java.util.List;
import java.util.Optional;

public interface IssueService {
    Optional<Issue> getIssueById(Long issueId) throws Exception;

    List<Issue> getIssuesByProjectId(Long projectId) throws Exception;


}
