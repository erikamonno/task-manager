package com.example.taskmanager.servicies;

import com.example.taskmanager.dto.ProjectDto;
import com.example.taskmanager.filters.ProjectFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ProjectService {

    ProjectDto insertProject(ProjectDto dto);
    ProjectDto readOneProject(UUID id);
    Page<ProjectDto> searchProject(Pageable pageable, ProjectFilter filter);
    void updateProject(UUID id, ProjectDto dto);
    void deleteProject(UUID id);
}
