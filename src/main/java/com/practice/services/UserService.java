package com.practice.services;

import com.practice.dto.UserDTO.UserCreateDTO;
import com.practice.dto.UserDTO.UserResponseDTO;

public interface UserService {

    UserResponseDTO saveUser(UserCreateDTO user);
}
