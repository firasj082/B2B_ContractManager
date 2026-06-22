package com.practice.dto;

import com.practice.models.Role;
import jakarta.validation.constraints.NotBlank;

public record BasicUserDTO(

        @NotBlank(message = "User must have a username")
        String username,
        Role role,
        boolean enabled
){}
