package com.example.taskmanager.mappers;

import com.example.taskmanager.dto.UserDto;
import com.example.taskmanager.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto (User entity);
    User toEntity (UserDto dto);
}
