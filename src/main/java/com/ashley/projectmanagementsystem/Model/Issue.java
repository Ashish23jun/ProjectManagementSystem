package com.ashley.projectmanagementsystem.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.jsonwebtoken.lang.Strings;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Comments;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


//This class has been created to store the details of the issue and the user to whom the issue is assigned.
//It has a ManyToOne relationship with the User class to store the user to whom the issue is assigned.
//It has a primary key id to uniquely identify the issue.


@Entity
@Data
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String description;
    private String status;

    private long projectId;
    private String priority;
    private LocalDate dueDate;

    private List<String >tags= new ArrayList<>()


    @ManyToOne
    private User assignee;

    @JsonIgnore
    @ManyToOne
    private Project project;

    @JsonIgnore
    @OneToMany(mappedBy = "issue",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Comment>comments= new ArrayList<>();
}
