package com.example;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class TodoRepositoryImpl implements TodoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Todo add(Todo todo) {
        entityManager.persist(todo);
        return todo;
    }

    @Override
    public List<Todo> findAll() {
        return entityManager.createQuery("SELECT t FROM Todo t", Todo.class).getResultList();
    }

    // Implementation of other methods like findById, update, delete, etc.
}
