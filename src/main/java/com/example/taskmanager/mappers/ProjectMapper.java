package com.example.taskmanager.mappers;

import com.example.taskmanager.dto.ProjectDto;
import com.example.taskmanager.entities.Project;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    ProjectDto toDto (Project entity);
    Project toEntity (ProjectDto dto);
}
