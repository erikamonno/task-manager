package com.example.taskmanager.controllers;

import com.example.taskmanager.dto.StatusDto;
import com.example.taskmanager.servicies.StatusService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("status")
public class StatusController {

    private final StatusService service;

    public StatusController(StatusService service) {
        this.service = service;
    }

    @PostMapping
    public StatusDto insertStatus(@RequestBody @Valid StatusDto dto) {
        return service.insertStatus(dto);
    }

    @GetMapping("{id}")
    public StatusDto readOneStatus(@PathVariable(name = "id") UUID id) {
        return service.readOneStatus(id);
    }

    @GetMapping
    public List<StatusDto> findAll() {
        return service.readAll();
    }

    @PutMapping("{id}")
    public void updateStatus(@RequestBody @Valid StatusDto dto, @PathVariable(name = "id") UUID id) {
        service.updateStatus(id, dto);
    }

    @DeleteMapping("{id}")
    public void deleteStatus(@PathVariable UUID id) {
        service.deleteStatus(id);
    }
}
