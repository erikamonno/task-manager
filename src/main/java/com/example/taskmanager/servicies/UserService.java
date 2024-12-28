package com.example.taskmanager.servicies;

import com.example.taskmanager.dto.UserDto;
import com.example.taskmanager.filters.UserFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface UserService {

    UserDto insertUser(UserDto dto);
    UserDto readOneUser(UUID id);
    Page<UserDto> searchUser(Pageable pageable, UserFilter filter);
    void updateUser(UserDto dto, UUID id);
    void deleteUser(UUID id);
}
