package com.aniket.ordermanagement.service;

import com.aniket.ordermanagement.dto.UserRequestDto;
import com.aniket.ordermanagement.entity.User;
import com.aniket.ordermanagement.exception.ResourceAlreadyExistsException;
import com.aniket.ordermanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public User createUser(UserRequestDto dto){
        userRepository.findByEmail(dto.getEmail())
                .ifPresent(user -> {
                    throw new ResourceAlreadyExistsException("Email already exists");
                });

        User user = User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .build();
        return userRepository.save(user);
    }
}
