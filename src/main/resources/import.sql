-- Schema
CREATE TABLE IF NOT EXISTS todo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    task VARCHAR(255),
    completed BOOLEAN NOT NULL
);

-- Data
INSERT INTO todo (task, completed) VALUES ('Task 1', false);
INSERT INTO todo (task, completed) VALUES ('Task 2', true);
