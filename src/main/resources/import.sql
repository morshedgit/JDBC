-- Schema
CREATE TABLE IF NOT EXISTS todos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    task VARCHAR(255),
    completed BOOLEAN NOT NULL
);

-- Data
INSERT INTO todos (task, completed) VALUES ('Task 1', false);
INSERT INTO todos (task, completed) VALUES ('Task 2', true);
