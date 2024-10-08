package com.ashley.projectmanagementsystem.Repository;

import com.ashley.projectmanagementsystem.Model.Project;
import com.ashley.projectmanagementsystem.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    // List<Project>findByOwner(User user);

    List<Project> findByNameContainingAndTeamContains(String partialName, User user);
    //
    // @Query("SELECT p FROM Project p join p.team t where t=:user")
    // List<Project>findProjectByTeam(@Param("user") User user);

    List<Project> findByTeamContainingOrOwner(User user, User Owner);

}
