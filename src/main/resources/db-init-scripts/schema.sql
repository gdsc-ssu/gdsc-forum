CREATE TABLE Users(
                     id INT NOT NULL AUTO_INCREMENT,
                     name VARCHAR(255) NOT NULL,
                     email VARCHAR(255) NOT NULL,
                     image VARCHAR(255),
                     role VARCHAR(64) NOT NULL,
                     introduction VARCHAR(1024),
                     age INT,
                     department VARCHAR(100),
                     link VARCHAR(255),
                     created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                     updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                     PRIMARY KEY(id)
);

-- image 테이블 분리 필요
CREATE TABLE Posts(
                     id INT NOT NULL AUTO_INCREMENT,
                     title VARCHAR(256) NOT NULL,
                     content VARCHAR(2048) NOT NULL,
                     author_id INT NOT NULL,
                     created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                     updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                     PRIMARY KEY(id),
                     FOREIGN KEY (author_id) REFERENCES User(id)
);