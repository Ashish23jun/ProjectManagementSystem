package com.ashley.projectmanagementsystem.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ashley.projectmanagementsystem.Model.Issue;

public interface IssueRepository extends JpaRepository<Issue, Long> {

    public List<Issue> findByProjectId(Long projectId);

}
