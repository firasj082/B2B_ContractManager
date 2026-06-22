package com.practice.services;

import com.practice.dto.UserDTO;
import com.practice.dto.BasicUserDTO;

public interface UserService {

    BasicUserDTO createUser(UserDTO user);
}
