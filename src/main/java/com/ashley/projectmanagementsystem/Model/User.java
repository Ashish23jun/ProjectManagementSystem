package com.ashley.projectmanagementsystem.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

//This class has been created to store the details of the user and the issues assigned to the user.
//It has a OneToMany relationship with the Issue class to store the issues assigned to the user.
//It has a primary key id to uniquely identify the user.
//It has fields to store the email, password, fullname, and project size of the user.
//The project size field stores the number of projects the user is assigned to.
//The class also has a list of assigned issues to store the issues assigned to the user.
//This class is used to interact with the database and perform operations on the User entity.


@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;
    private String password;
    private String fullname;

    @JsonIgnore
    @OneToMany(mappedBy = "assignee",cascade = CascadeType.ALL)
    private List<Issue> assignedIssues= new ArrayList<>();

    private int projectSize;
}
