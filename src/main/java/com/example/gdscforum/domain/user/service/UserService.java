package com.example.gdscforum.domain.user.service;

import com.example.gdscforum.domain.user.dto.UserDto;
import com.example.gdscforum.domain.user.entity.User;
import com.example.gdscforum.domain.user.exception.UserNotFoundException;
import com.example.gdscforum.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserDto getUserDtoById(Integer id) {
        User user = userRepository.findById(id)
            .orElseThrow(UserNotFoundException::new);

        return UserDto.from(user);
    }
}
