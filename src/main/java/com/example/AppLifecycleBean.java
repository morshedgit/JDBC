package com.example;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import io.quarkus.runtime.StartupEvent;
import javax.sql.DataSource;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.Statement;
import java.util.stream.Collectors;

@ApplicationScoped
public class AppLifecycleBean {

    @Inject
    DataSource dataSource;

    void onStart(@Observes StartupEvent ev) {
        try (Connection connection = dataSource.getConnection()) {
            try (Statement stmt = connection.createStatement()) {
                String sql = readSqlStatements();
                // Execute your SQL statements here
                stmt.execute(sql);
                // Additional initialization SQL can be added here
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private String readSqlStatements() {
        try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("import.sql");
             BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
            return reader.lines().collect(Collectors.joining("\n"));
        } catch (Exception e) {
            throw new RuntimeException("Failed to read init.sql file", e);
        }
    }
}

