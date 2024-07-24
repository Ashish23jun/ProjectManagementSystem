package com.ashley.projectmanagementsystem.Model;

import jakarta.persistence.*;
import lombok.Data;



//This class has been created to store the details of the issue and the user to whom the issue is assigned.
//It has a ManyToOne relationship with the User class to store the user to whom the issue is assigned.
//It has a primary key id to uniquely identify the issue.


@Entity
@Data
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User assignee;
}
