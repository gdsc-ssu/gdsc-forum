INSERT INTO users (username, email, password, introduction, age, link, role, refresh_token) VALUES
('테스트1', 'gdsc@gmail.com', '$2a$10$lCQbhmv64ZTDT2L.e5HfH.Ik.sW5oeMMTkCQgci0Ze9X8w.xRLv1O', 'web/mobile member', 20, 'www.google.com', 'user', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyUm9sZSI6InVzZXIiLCJleHAiOjE3MTgxODQ3NTAsInVzZXJJZCI6MX0.Yv1O6Uk5ynRtLM5Wh_juhUuq6vNLq9QT7T9w8DcU9Aw'),
('테스트2', 'gdsc2@gmail.com', '$2a$10$lCQbhmv64ZTDT2L.e5HfH.Ik.sW5oeMMTkCQgci0Ze9X8w.xRLv1O', 'server/cloud member', 22, 'www.google.com', 'user', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyUm9sZSI6InVzZXIiLCJleHAiOjE3MTgzNTAzODcsInVzZXJJZCI6Mn0.dxbTWt0ctsRQzMxwqYhQYyNTk21nJaMpFzOlSudfqgU');

INSERT INTO post (title, content, user_id) VALUES
('Introduction to Java', 'This post covers the basics of Java programming language.', 1),
('Getting Started with Spring Boot', 'A guide on how to create a Spring Boot application.', 1),
('Understanding JPA', 'Detailed explanation of Java Persistence API and how to use it.', 2),
('REST API with Spring Boot', 'Learn how to create RESTful services using Spring Boot.', 2);