package com.example.gdscforum.domain.user.service;

import com.example.gdscforum.domain.user.dto.UserDto;
import com.example.gdscforum.domain.user.entity.User;
import com.example.gdscforum.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RawUserService {
    private final UserRepository userRepository;


    // controller 사용 금지
    public Optional<User> getOptionalUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // controller 사용 금지
    @Transactional
    public User createUser(String username, String email, String password, String introduction, Integer age, String link, String role) {
        // role이 admin이면 throw / role이 null이면 user로

        User user = User.builder()
            .username(username)
            .email(email)
            .password(password)
            .introduction(introduction)
            .age(age)
            .link(link)
            .role(role)
            .refreshToken(null)
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .build();

        user = userRepository.save(user);

        return user;
    }

    // controller 사용 금지
    public User getUserById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));
    }
}
