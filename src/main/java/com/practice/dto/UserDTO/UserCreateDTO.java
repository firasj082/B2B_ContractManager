package com.practice.dto.UserDTO;

import com.practice.models.Role;

public record UserCreateDTO(
        String username,
        String password,
        Role role,
        boolean enabled
){}
