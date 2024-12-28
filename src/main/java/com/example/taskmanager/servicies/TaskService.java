package com.example.taskmanager.servicies;

import com.example.taskmanager.dto.TaskDto;
import com.example.taskmanager.filters.TaskFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface TaskService {

    TaskDto insertTask(TaskDto dto);
    TaskDto readOneTask(UUID id);
    Page<TaskDto> searchTask(Pageable pageable, TaskFilter filter);
    void updateTask(UUID id, TaskDto dto);
    void deleteTask(UUID id);
}
