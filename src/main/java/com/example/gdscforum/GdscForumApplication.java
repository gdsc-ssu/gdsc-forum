package com.example.gdscforum;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
@Slf4j
public class GdscForumApplication {

    public static void main(String[] args) {
        SpringApplication.run(GdscForumApplication.class, args);

        var now = LocalDateTime.now().toString();
        log.info("Application started at {}", now);    }

}
