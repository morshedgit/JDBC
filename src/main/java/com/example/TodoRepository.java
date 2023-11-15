package com.example;

import java.util.List;

public interface TodoRepository {
    Todo add(Todo todo);
    List<Todo> findAll();
    // Other data access methods like findById, update, delete, etc.
}
