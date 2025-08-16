package com.todoApp.TodoAPP.repository;

import com.todoApp.TodoAPP.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    //Find user by username
    Optional<User> findByUsername(String username);
    //check if user exists already
    boolean existsByUsername(String username);
    //check if email already exists
    boolean  existsByEmail(String email);
}
