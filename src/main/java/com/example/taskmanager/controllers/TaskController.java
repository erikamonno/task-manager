package com.example.taskmanager.controllers;

import com.example.taskmanager.dto.TaskDto;
import com.example.taskmanager.filters.TaskFilter;
import com.example.taskmanager.servicies.TaskService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("task")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping
    public TaskDto insertTask(@RequestBody TaskDto dto) {
        return service.insertTask(dto);
    }

    @GetMapping("{id}")
    public TaskDto readOneTask(@PathVariable(name = "id") UUID id) {
        return service.readOneTask(id);
    }

    @GetMapping
    public Page<TaskDto> searchTask(@PageableDefault Pageable pageable, TaskFilter filter) {
        return service.searchTask(pageable, filter);
    }

    @PutMapping("{id}")
    public void updateTask(@RequestBody TaskDto dto, @PathVariable(name = "id") UUID id) {
        service.updateTask(id, dto);
    }

    @DeleteMapping("{id}")
    public void deleteTask(@PathVariable(name = "id") UUID id) {
        service.deleteTask(id);
    }
}
