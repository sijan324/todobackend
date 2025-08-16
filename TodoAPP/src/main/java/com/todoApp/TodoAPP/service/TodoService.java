package com.todoApp.TodoAPP.service;

import com.todoApp.TodoAPP.model.Todo;
import com.todoApp.TodoAPP.model.User;
import com.todoApp.TodoAPP.repository.TodoRepository;
import com.todoApp.TodoAPP.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    private final TodoRepository todoRepository;
    public TodoService(TodoRepository todoRepository){
        this.todoRepository=todoRepository;
    }

public Todo createTodo(Todo todo, User user){
    todo.setUser(user);
    return  todoRepository.save(todo);

}
// get all todos for a specific user
public List<Todo> getTodosByUser(User user) {
    return  todoRepository.findByUser(user);
}
public Todo updateTodo(Long id, Todo updatedTodo){
       Optional<Todo> optionalTodo= todoRepository.findById(id);/// optional is used to avoid null pointer exceptions and also checks es=xisting user is ther or not
    if (optionalTodo.isEmpty()) {
        throw new RuntimeException("Todo not found ");
    }
    Todo todo = optionalTodo.get();
    todo.setTitle(updatedTodo.getTitle());
    todo.setDescription(updatedTodo.getDescription());
    todo.setCompleted(updatedTodo.isCompleted());
    return  todoRepository.save(todo);
}
 public void deleteTodo(Long id){
        if(!todoRepository.existsById(id)){
            throw new RuntimeException("Todo not found");

        }
        todoRepository.deleteById(id);
}
}