package com.aniket.ordermanagement.controller;

import com.aniket.ordermanagement.dto.ApiResponse;
import com.aniket.ordermanagement.dto.LoginRequestDto;
import com.aniket.ordermanagement.dto.LoginResponseDto;
import com.aniket.ordermanagement.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponseDto>>login(@RequestBody LoginRequestDto request) {
        return ResponseEntity.ok(
                ApiResponse.<LoginResponseDto>builder()
                    .success(true)
                    .message("Login successful")
                    .data(authService.login(request))
                    .build()
        );
    }
}
