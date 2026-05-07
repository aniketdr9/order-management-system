package com.aniket.ordermanagement.service;

import com.aniket.ordermanagement.dto.LoginRequestDto;
import com.aniket.ordermanagement.dto.LoginResponseDto;
import com.aniket.ordermanagement.entity.User;
import com.aniket.ordermanagement.repository.UserRepository;
import com.aniket.ordermanagement.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder  bCryptPasswordEncoder;
    private final JwtUtil jwtUtil;

    public LoginResponseDto login(LoginRequestDto request){
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid Email"));

        if(!bCryptPasswordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {
            throw new RuntimeException("Invalid Password");
        }
        String token = jwtUtil.generateToken(user.getEmail());
        return new LoginResponseDto(token);
    }
}
