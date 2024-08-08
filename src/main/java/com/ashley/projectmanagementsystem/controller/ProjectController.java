package com.ashley.projectmanagementsystem.controller;

import com.ashley.projectmanagementsystem.Model.Chat;
import com.ashley.projectmanagementsystem.Model.Project;
import com.ashley.projectmanagementsystem.Model.User;
import com.ashley.projectmanagementsystem.response.MessageResponse;
import com.ashley.projectmanagementsystem.service.ProjectService;
import com.ashley.projectmanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<Project>> getProjects(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String tag,
            @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        List<Project> projects = projectService.getProjectByTeam(user, category, tag);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<Project> getProjectById(
            @PathVariable Long projectId,
            @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        Project project = projectService.getProjectById(projectId);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    public ResponseEntity<Project> createProject(
            @PathVariable Long projectId,
            @RequestHeader("Authorization") String jwt,
            @RequestBody Project project) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        Project createdProject = projectService.createProject(project, user);
        return new ResponseEntity<>(createdProject, HttpStatus.OK);

    }

    @PostMapping("/{projectId}")
    public ResponseEntity<Project> updateProject(
            @PathVariable Long projectId,
            @RequestHeader("Authorization") String jwt,
            @RequestBody Project project) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        Project updateProject = projectService.updateProject(project, projectId);
        return new ResponseEntity<>(updateProject, HttpStatus.OK);

    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<MessageResponse> deleteProject(
            @PathVariable Long projectId,
            @RequestHeader("Authorization") String jwt)
            throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        projectService.deleteProject(projectId, user.getId());
        MessageResponse res = new MessageResponse("Project deleted successfully");
        return new ResponseEntity<>(res, HttpStatus.OK);

    }

    @GetMapping("/search")
    public ResponseEntity<List<Project>> searchProject(
            @RequestParam(required = false) String keyword,
            @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        List<Project> projects = projectService.searchProject(keyword, user);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping("/{projectId}/chat")
    public ResponseEntity<Chat> getChatByProjectId(
            @PathVariable Long projectId,
            @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        Chat chat = projectService.getChatByProjectId(projectId);
        return new ResponseEntity<>(chat, HttpStatus.OK);
    }
}
