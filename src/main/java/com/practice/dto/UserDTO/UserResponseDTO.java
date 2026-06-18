package com.practice.dto.UserDTO;

import com.practice.models.Role;

public record UserResponseDTO(
        String username,
        Role role,
        boolean enabled
) {}
