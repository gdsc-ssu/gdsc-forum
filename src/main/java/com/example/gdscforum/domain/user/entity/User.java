package com.example.gdscforum.domain.user.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 256)
    private String username;

    @Column(nullable = false, length = 256)
    private String email;

    @Column(nullable = false, length = 256)
    private String password;

    @Column(length = 256)
    private String introduction;

    @Column
    private Integer age;

    @Column(length = 2048)
    private String link;

    @Column(nullable = false, length = 256)
    private String role;

    @Column(length = 1024)
    private String refreshToken;

    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;

    @Builder
    public User(String username, String email, String password, String introduction, Integer age, String link, String role, String refreshToken, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.introduction = introduction;
        this.age = age;
        this.link = link;
        this.role = role;
        this.refreshToken = refreshToken;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}