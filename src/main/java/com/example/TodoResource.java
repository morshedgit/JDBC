package com.example;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Path("/todos")
public class TodoResource {

    @Inject
    javax.sql.DataSource dataSource;

    @POST
    @Path("/add")
    @Produces(MediaType.TEXT_PLAIN)
    public Response addTodo(String task) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO todos (task, completed) VALUES (?, false)";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, task);
                stmt.executeUpdate();
            }
            return Response.ok("Todo added successfully").build();
        } catch (SQLException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTodos() {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM todos";
            try (PreparedStatement stmt = connection.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {
                List<Todo> todos = new ArrayList<>();
                while (rs.next()) {
                    Todo todo = new Todo();
                    todo.setId(rs.getInt("id"));
                    todo.setTask(rs.getString("task"));
                    todo.setCompleted(rs.getBoolean("completed"));
                    todos.add(todo);
                }
                return Response.ok(todos).build();
            }
        } catch (SQLException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    // Additional CRUD methods can be implemented similarly

    // Inner class to represent a Todo
    public static class Todo {
        private int id;
        private String task;
        private boolean completed;

        // getters and setters
        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
        }
        public String getTask() {
            return task;
        }
        public void setTask(String task) {
            this.task = task;
        }
        public boolean isCompleted() {
            return completed;
        }
        public void setCompleted(boolean completed) {
            this.completed = completed;
        }
    }
}
