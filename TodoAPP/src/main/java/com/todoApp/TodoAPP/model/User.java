package com.todoApp.TodoAPP.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    @Column(unique=true)
    private  String email;
    @Column(unique=true)
    private String password;
    @ElementCollection(fetch =  FetchType.EAGER)
    private Set<String> roles;//roles for user or admin

}

