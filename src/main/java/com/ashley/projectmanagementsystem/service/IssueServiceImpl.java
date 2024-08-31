package com.ashley.projectmanagementsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashley.projectmanagementsystem.Model.Issue;
import com.ashley.projectmanagementsystem.Model.Project;
import com.ashley.projectmanagementsystem.Model.User;
import com.ashley.projectmanagementsystem.Repository.IssueRepository;
import com.ashley.projectmanagementsystem.request.IssueRequest;

@Service
public class IssueServiceImpl implements IssueService {

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @Override
    public Optional<Issue> getIssueById(Long issueId) throws Exception {
        Optional<Issue> issue = issueRepository.findById(issueId);
        if (issue.isPresent()) {
            return issue;
        }
        throw new Exception("No issues find with issueId" + issueId);
    }

    @Override
    public List<Issue> getIssuesByProjectId(Long projectId) throws Exception {
        return issueRepository.findByProjectId(projectId);
    }

    @Override
    public Issue createIssue(IssueRequest issueRequest, User user) throws Exception {
        Project project = projectService.getProjectById(issueRequest.getProjectId());
        Issue issue = new Issue();
        issue.setTitle(issueRequest.getTitle());
        issue.setDescription(issueRequest.getDescription());
        issue.setPriority(issueRequest.getPriority());
        issue.setStatus(issueRequest.getStatus());
        issue.setProjectId(issueRequest.getProjectId());
        issue.setDueDate(issueRequest.getDueDate());

        issue.setProject(project);

        return issueRepository.save(issue);
    }

    @Override
    public void deleteIssue(Long issueId, Long userId) throws Exception {
        Optional<Issue> issue = getIssueById(issueId);
        if (!issue.isPresent()) {
            throw new Exception("Issue not found with ID: " + issueId);
        }
        issueRepository.deleteById(issueId);
    }

    // @Override
    // public List<Issue> getIssuesByAssigneeId(Long assigneeId) throws Exception {
    // return null;
    // }

    @Override
    public Issue addUserToIssue(Long issueId, Long userId) throws Exception {
        User user = userService.findUserById(userId);
        Issue issue = getIssueById(issueId).get();
        issue.setAssignee(user);
        return issueRepository.save(issue);
    }

    @Override
    public Issue updateStatus(Long issueId, String status) throws Exception {
        Issue issue = getIssueById(issueId).get();
        issue.setStatus(status);
        return issueRepository.save(issue);
    }

    // @Override
    // public List<Issue> getIssuesByAssigneeId(Long assigneeId) throws Exception {
    // // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method
    // 'getIssuesByAssigneeId'");
    // }

}