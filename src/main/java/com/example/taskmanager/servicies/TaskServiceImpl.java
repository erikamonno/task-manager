package com.example.taskmanager.servicies;

import com.example.taskmanager.dto.StatusDto;
import com.example.taskmanager.dto.TaskDto;
import com.example.taskmanager.dto.UserDto;
import com.example.taskmanager.entities.Task;
import com.example.taskmanager.exceptions.TaskNotFoundException;
import com.example.taskmanager.filters.TaskFilter;
import com.example.taskmanager.mappers.ProjectMapper;
import com.example.taskmanager.mappers.StatusMapper;
import com.example.taskmanager.mappers.TaskMapper;
import com.example.taskmanager.mappers.UserMapper;
import com.example.taskmanager.repositories.CriteriaBuilderHelper;
import com.example.taskmanager.repositories.TaskRepository;
import com.example.taskmanager.specifications.TaskSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final ProjectMapper projectMapper;
    private final ProjectService projectService;
    private final StatusMapper statusMapper;
    private final StatusService statusService;
    private final UserService userService;
    private final UserMapper userMapper;
    private final CriteriaBuilderHelper cbh;

    public TaskServiceImpl(TaskRepository taskRepository, TaskMapper taskMapper, ProjectMapper projectMapper, ProjectService projectService, StatusMapper statusMapper, StatusService statusService, UserService userService, UserMapper userMapper, CriteriaBuilderHelper cbh) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
        this.projectMapper = projectMapper;
        this.projectService = projectService;
        this.statusMapper = statusMapper;
        this.statusService = statusService;
        this.userService = userService;
        this.userMapper = userMapper;
        this.cbh = cbh;
    }

    @Override
    public TaskDto insertTask(TaskDto dto) {
        Task entity = new Task();
        var projectDto = projectService.readOneProject(dto.getProject().getId());
        var createdBy = userService.readOneUser(dto.getCreatedBy().getId());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setStartedTimestamp(dto.getStartedTimestamp());
        entity.setEndTimestamp(dto.getEndTimestamp());
        entity.setProject(projectMapper.toEntity(projectDto));
        StatusDto statusDto;  // sto dichiarando la variabile
        if(dto.getStatus()==null) {
            statusDto = statusService.readOneStatus("New");
        }else{
            statusDto = statusService.readOneStatus(dto.getStatus().getId());
        }
        entity.setStatus(statusMapper.toEntity(statusDto));

        UserDto assignedTo = null;
        if(dto.getAssignedTo()!=null) {
            assignedTo = userService.readOneUser(dto.getAssignedTo().getId());
        }
        entity.setAssignedTo(userMapper.toEntity(assignedTo));
        entity.setCreatedBy(userMapper.toEntity(createdBy));
        entity.setEstimatedTime(dto.getEstimatedTime());
        entity = taskRepository.save(entity);
        return taskMapper.toDto(entity);
    }

    @Override
    public TaskDto readOneTask(UUID id) {
        var oEntity = taskRepository.findById(id);
        if(oEntity.isEmpty()) {
            throw new TaskNotFoundException("Task not found");
        }
        var entity = oEntity.get();
        return taskMapper.toDto(entity);
    }

    @Override
    public Page<TaskDto> searchTask(Pageable pageable, TaskFilter filter) {
        return taskRepository.findAll(new TaskSpecification(filter, cbh.getHibernateCriteriaBuilder()), pageable).map(task -> taskMapper.toDto(task));
    }

    @Override
    @Transactional
    public void updateTask(UUID id, TaskDto dto) {
        var oEntity = taskRepository.findById(id);
        if(oEntity.isEmpty()) {
            throw new TaskNotFoundException("Task not found");
        }
        var entity = oEntity.get();
        var projectDto = projectService.readOneProject(dto.getProject().getId());
        var statusDTo = statusService.readOneStatus(dto.getStatus().getId());
        var createdBy = userService.readOneUser(dto.getCreatedBy().getId());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setStartedTimestamp(dto.getStartedTimestamp());
        entity.setEndTimestamp(dto.getEndTimestamp());
        entity.setProject(projectMapper.toEntity(projectDto));
        entity.setStatus(statusMapper.toEntity(statusDTo));
        UserDto assignedTo = null;
        if(dto.getAssignedTo()!=null) {
            assignedTo = userService.readOneUser(dto.getAssignedTo().getId());
        }
        entity.setAssignedTo(userMapper.toEntity(assignedTo));
        entity.setCreatedBy(userMapper.toEntity(createdBy));
        entity.setEstimatedTime(dto.getEstimatedTime());
    }
    @Override
    public void deleteTask(UUID id) {
        taskRepository.deleteById(id);
    }
}
