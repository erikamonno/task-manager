package com.example.taskmanager.controllers;

import com.example.taskmanager.dto.ProjectDto;
import com.example.taskmanager.filters.ProjectFilter;
import com.example.taskmanager.servicies.ProjectService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("project")
public class ProjectController {

    private final ProjectService service;

    public ProjectController(ProjectService service) {
        this.service = service;
    }

    @PostMapping
    public ProjectDto insertProject(@RequestBody @Valid ProjectDto dto) {
        return service.insertProject(dto);
    }

    @GetMapping("{id}")
    public ProjectDto readOneProject(@PathVariable(name = "id") UUID id) {
        return service.readOneProject(id);
    }

    @GetMapping
    public Page<ProjectDto> searchProject(@PageableDefault Pageable pageable, @Valid ProjectFilter filter) {
        return service.searchProject(pageable, filter);
    }

    @PutMapping("{id}")
    public void updateProject(@RequestBody ProjectDto dto, @PathVariable(name = "id") UUID id) {
        service.updateProject(id, dto);
    }

    @DeleteMapping("{id}")
    public void deleteProject(@PathVariable UUID id) {
        service.deleteProject(id);
    }

}
