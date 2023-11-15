package com.example;

import jakarta.transaction.Transactional;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/todos")
public class TodoResource {

    @Inject
    private TodoService todoService;

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTodo(TodoDto todoDto) {
        Todo newTodo = todoService.addTodo(todoDto);
        return Response.status(Response.Status.CREATED).entity(newTodo).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Todo> getAllTodos() {
        return todoService.getAllTodos();
    }

    // Other endpoints
}
