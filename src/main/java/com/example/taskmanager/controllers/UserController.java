package com.example.taskmanager.controllers;

import com.example.taskmanager.dto.UserDto;
import com.example.taskmanager.filters.UserFilter;
import com.example.taskmanager.servicies.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public UserDto insertUser(@RequestBody @Valid UserDto dto) {
        return service.insertUser(dto);
    }

    @GetMapping("{id}")
    public UserDto readOneUser(@PathVariable(name = "id") UUID id) {
        return service.readOneUser(id);
    }

    @GetMapping
    public Page<UserDto> searchUser(@PageableDefault Pageable pageable, @Valid UserFilter filter) {
        return service.searchUser(pageable, filter);
    }

    @PutMapping("{id}")
    public void updateUser(@PathVariable(name = "id") UUID id, @RequestBody @Valid UserDto dto) {
        service.updateUser(dto, id);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable(name = "id") UUID id) {
        service.deleteUser(id);
    }
}
