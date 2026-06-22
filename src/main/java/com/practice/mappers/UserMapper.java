package com.practice.mappers;

import com.practice.dto.UserDTO;
import com.practice.dto.BasicUserDTO;
import com.practice.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "role", source = "role")
    BasicUserDTO toDTO(User user);
    User toEntity(UserDTO userDTO);

    List<BasicUserDTO> toDTOList(List<User> users);
    List<User> toEntityList(List<BasicUserDTO> basicUserDTOS);
}
