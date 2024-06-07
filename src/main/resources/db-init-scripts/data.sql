INSERT INTO users (username, email, password, introduction, age, link, role, refresh_token, created_at, updated_at) VALUES
('테스트1', 'gdsc@gmail.com', '$2a$10$lCQbhmv64ZTDT2L.e5HfH.Ik.sW5oeMMTkCQgci0Ze9X8w.xRLv1O', 'web/mobile member', 20, 'www.google.com', 'user', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyUm9sZSI6InVzZXIiLCJleHAiOjE3MTgxODQ3NTAsInVzZXJJZCI6MX0.Yv1O6Uk5ynRtLM5Wh_juhUuq6vNLq9QT7T9w8DcU9Aw', '2024-06-05 18:32:30.531738', '2024-06-05 18:32:30.531749'),
('테스트2', 'gdsc2@gmail.com', '$2a$10$lCQbhmv64ZTDT2L.e5HfH.Ik.sW5oeMMTkCQgci0Ze9X8w.xRLv1O', 'server/cloud member', 22, 'www.google.com', 'user', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyUm9sZSI6InVzZXIiLCJleHAiOjE3MTgzNTAzODcsInVzZXJJZCI6Mn0.dxbTWt0ctsRQzMxwqYhQYyNTk21nJaMpFzOlSudfqgU', '2024-06-05 18:32:30.531738', '2024-06-05 18:32:30.531749');

INSERT INTO post (title, content, user_id) VALUES
('Introduction to Java', 'This post covers the basics of Java programming language.', 1),
('Getting Started with Spring Boot', 'A guide on how to create a Spring Boot application.', 1),
('Understanding JPA', 'Detailed explanation of Java Persistence API and how to use it.', 1),
('REST API with Spring Boot', 'Learn how to create RESTful services using Spring Boot.', 1),
('Database Transactions in Spring', 'This post explains how to handle database transactions in Spring.', 1),
('Spring Boot Security', 'Overview of security features in Spring Boot.', 2),
('Unit Testing with JUnit', 'Best practices for writing unit tests using JUnit.', 2),
('Dockerizing Spring Boot Application', 'Steps to containerize your Spring Boot application using Docker.', 2),
('Spring Boot and Kafka', 'Integrating Apache Kafka with Spring Boot for real-time data processing.', 2),
('Microservices Architecture', 'Introduction to microservices architecture and its advantages.', 2);
