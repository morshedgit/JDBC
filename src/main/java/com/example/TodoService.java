package com.example;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;

@ApplicationScoped
public class TodoService {

    @Inject
    private TodoRepository todoRepository;

    public Todo addTodo(TodoDto todoDto) {
        Todo newTodo = new Todo();
        newTodo.setTask(todoDto.getTask());
        newTodo.setCompleted(false);
        return todoRepository.add(newTodo);
    }

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    // Other service methods
}
