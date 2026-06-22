package com.practice.dto;

import jakarta.validation.constraints.NotBlank;

public record UserDTO(

        @NotBlank(message = "User must have a username")
        String username,
        @NotBlank(message = "User must have a password")
        String password
){}
