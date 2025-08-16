package com.todoApp.TodoAPP.controller;

import com.todoApp.TodoAPP.model.Todo;
import com.todoApp.TodoAPP.model.User;
import com.todoApp.TodoAPP.service.TodoService;
import com.todoApp.TodoAPP.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService todoService;
    private final UserService userService;

    public TodoController(TodoService todoService, UserService userService) {
        this.todoService = todoService;
        this.userService = userService;
    }

    // Create a new Todo for the authenticated user
    @PostMapping
    public Todo createTodo(@RequestBody Todo todo) {
        User user = getAuthenticatedUser();
        return todoService.createTodo(todo, user);
    }

    // Get all Todos for the authenticated user
    @GetMapping
    public List<Todo> getTodos() {
        User user = getAuthenticatedUser();
        return todoService.getTodosByUser(user);
    }

    // Update a Todo by ID

    // Delete a Todo by ID
    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable Long id) {
        User user = getAuthenticatedUser();
        // Optional: you can check if the Todo belongs to this user
        todoService.deleteTodo(id);
    }

    // Helper method to get the authenticated user from JWT
    private User getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return userService.findByUsername(username);
    }
}
