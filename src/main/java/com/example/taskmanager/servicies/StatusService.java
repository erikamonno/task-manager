package com.example.taskmanager.servicies;

import com.example.taskmanager.dto.StatusDto;

import java.util.List;
import java.util.UUID;

public interface StatusService {

    StatusDto insertStatus(StatusDto dto);
    StatusDto readOneStatus(UUID id);
    StatusDto readOneStatus(String name);
    List<StatusDto> readAll();
    void updateStatus(UUID id, StatusDto dto);
    void deleteStatus(UUID id);
}
