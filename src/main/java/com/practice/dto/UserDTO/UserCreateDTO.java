package com.practice.dto.UserDTO;

public record UserCreateDTO(
        String username,
        String password,
        String roles
){}
