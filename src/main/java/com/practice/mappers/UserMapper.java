package com.practice.mappers;

import com.practice.dto.UserDTO.UserCreateDTO;
import com.practice.dto.UserDTO.UserResponseDTO;
import com.practice.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "Spring")
public interface UserMapper {

    @Mapping(target = "roles", source = "roles")
    UserCreateDTO toCreateDTO(User user);
    @Mapping(target = "roles", source = "roles")
    UserResponseDTO toResponseDTO(User user);
    User toEntity(UserCreateDTO userCreateDTO);
}
