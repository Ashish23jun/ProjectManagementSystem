package com.ashley.projectmanagementsystem.Repository;

import com.ashley.projectmanagementsystem.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;



//This interface extends the JpaRepository interface which provides all the CRUD operations for the User entity.
//It also provides a method to find a user by email.
//This interface is used to interact with the database and perform operations on the User entity.
public interface UserRepository extends JpaRepository <User,Long>{
    User findByEmail(String email);
}
