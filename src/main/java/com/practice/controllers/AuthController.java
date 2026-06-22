package com.practice.controllers;

import com.practice.dto.BasicUserDTO;
import com.practice.dto.UserDTO;
import com.practice.responses.ApiResponse;
import com.practice.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<BasicUserDTO>> createUser(@Valid @RequestBody UserDTO userDTO) {
        BasicUserDTO saved = userService.createUser(userDTO);
        ApiResponse<BasicUserDTO> response = new ApiResponse<>(true, "The user was created successfully.", saved);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
