package com.example.gdscforum.domain.user.service;

import com.example.gdscforum.domain.user.dto.UserResponse;
import com.example.gdscforum.domain.user.entity.User;
import com.example.gdscforum.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserResponse getUser(Integer userId) {
        User user = userRepository.findById(userId).get();

        return UserResponse.from(user);
    }
}
