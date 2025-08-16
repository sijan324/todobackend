package com.todoApp.TodoAPP.repository;

import com.todoApp.TodoAPP.model.Todo;
import com.todoApp.TodoAPP.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByUser(User user);

}
