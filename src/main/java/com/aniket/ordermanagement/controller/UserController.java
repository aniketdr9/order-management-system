package com.aniket.ordermanagement.controller;

import com.aniket.ordermanagement.dto.UserRequestDto;
import com.aniket.ordermanagement.entity.User;
import com.aniket.ordermanagement.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public User createUser(@Valid @RequestBody UserRequestDto request){
        return userService.createUser(request);
    }
}
