package com.practice.controllers;

import com.practice.dto.UserDTO.UserCreateDTO;
import com.practice.dto.UserDTO.UserResponseDTO;
import com.practice.responses.ApiResponse;
import com.practice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("${public.base.path}${user.base.path}")
public class UserRegistrationController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<ApiResponse<UserResponseDTO>> saveUser(@RequestBody UserCreateDTO userCreateDTO) {
        UserResponseDTO saved = userService.saveUser(userCreateDTO);
        ApiResponse<UserResponseDTO> response = new ApiResponse<>(true, "The user was created successfully.", saved);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
