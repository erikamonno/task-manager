package com.example.taskmanager.servicies;

import com.example.taskmanager.dto.ProjectDto;
import com.example.taskmanager.entities.Project;
import com.example.taskmanager.exceptions.ProjectNotFoundException;
import com.example.taskmanager.filters.ProjectFilter;
import com.example.taskmanager.mappers.ProjectMapper;
import com.example.taskmanager.repositories.ProjectRepository;
import com.example.taskmanager.specifications.ProjectSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository repository;
    private final ProjectMapper mapper;

    public ProjectServiceImpl(ProjectRepository repository, ProjectMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ProjectDto insertProject(ProjectDto dto) {
        Project entity = new Project();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    @Override
    public ProjectDto readOneProject(UUID id) {
        var oEntity = repository.findById(id);
        if(oEntity.isEmpty()) {
            throw new ProjectNotFoundException("Project not found");
        }
        var entity = oEntity.get();
        return mapper.toDto(entity);
    }

    @Override
    public Page<ProjectDto> searchProject(Pageable pageable, ProjectFilter filter) {
        return repository.findAll(new ProjectSpecification(filter), pageable).map(project -> mapper.toDto(project));
    }

    @Override
    @Transactional
    public void updateProject(UUID id, ProjectDto dto) {
        var oEntity = repository.findById(id);
        if(oEntity.isEmpty()) {
            throw new ProjectNotFoundException("Project not found");
        }
        var entity = oEntity.get();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
    }

    @Override
    public void deleteProject(UUID id) {
        repository.deleteById(id);
    }
}
