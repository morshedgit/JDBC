package com.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/todos")
public class TodoResource {

    @PersistenceContext
    EntityManager entityManager;

    @POST
    @Path("/add")
    @Transactional
    @Produces(MediaType.TEXT_PLAIN)
    public Response addTodo(String task) {
        Todo newTodo = new Todo();
        newTodo.setTask(task);
        newTodo.setCompleted(false);
        entityManager.persist(newTodo);
        return Response.ok("Todo added successfully").build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Todo> getAllTodos() {
        return entityManager.createQuery("SELECT t FROM Todo t", Todo.class).getResultList();
    }
}
