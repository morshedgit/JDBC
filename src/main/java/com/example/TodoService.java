package com.example;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class TodoService {

    @PersistenceContext
    private EntityManager entityManager;

    public Todo addTodo(TodoDto todoDto) {
        // Validate and process the todoDto
        // Create and persist the Todo entity
        Todo newTodo = new Todo();
        newTodo.setTask(todoDto.getTask());
        newTodo.setCompleted(false);
        entityManager.persist(newTodo);
        return newTodo;
    }

    public List<Todo> getAllTodos() {
        return entityManager.createQuery("SELECT t FROM Todo t", Todo.class).getResultList();
    }

    // Other service methods
}
