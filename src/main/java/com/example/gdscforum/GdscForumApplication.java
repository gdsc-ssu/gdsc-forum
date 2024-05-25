package com.example.gdscforum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GdscForumApplication {

    public static void main(String[] args) {
        SpringApplication.run(GdscForumApplication.class, args);

        System.out.println("Hello World");
    }

}
