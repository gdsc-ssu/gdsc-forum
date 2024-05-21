create TABLE post(
                     id INT NOT NULL AUTO_INCREMENT,
                     title VARCHAR(256) NOT NULL,
                     content VARCHAR(2048) NOT NULL,
                     created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                     updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                     PRIMARY KEY(id),
);