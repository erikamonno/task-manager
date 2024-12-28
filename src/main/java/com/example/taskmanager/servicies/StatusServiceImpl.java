package com.example.taskmanager.servicies;

import com.example.taskmanager.dto.StatusDto;
import com.example.taskmanager.entities.Status;
import com.example.taskmanager.exceptions.StatusNotFoundException;
import com.example.taskmanager.mappers.StatusMapper;
import com.example.taskmanager.repositories.StatusRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class StatusServiceImpl implements StatusService {

    private final StatusRepository repository;

    private final StatusMapper mapper;

    public StatusServiceImpl(StatusRepository repository, StatusMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public StatusDto insertStatus(StatusDto dto) {
        Status entity = new Status();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    @Override
    public StatusDto readOneStatus(UUID id) {
        var oEntity = repository.findById(id);
        if(oEntity.isEmpty()) {
            throw new StatusNotFoundException("Status not found");
        }
        var entity = oEntity.get();
        return mapper.toDto(entity);
    }

    @Override
    public StatusDto readOneStatus(String name) {
        var oStatus = repository.findByName(name);
        if(oStatus.isEmpty()) {
            throw new StatusNotFoundException("Status not found");
        }
        var entity = oStatus.get();
        return mapper.toDto(entity);
    }

    @Override
    public List<StatusDto> readAll() {
        return repository.findAll().stream().map(status -> mapper.toDto(status)).toList();
    }

    @Override
    @Transactional
    public void updateStatus(UUID id, StatusDto dto) {
        var oEntity = repository.findById(id);
        if(oEntity.isEmpty()) {
            throw new StatusNotFoundException("Stats not found");
        }
        var entity = oEntity.get();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
    }

    @Override
    public void deleteStatus(UUID id) {
        repository.deleteById(id);
    }
}
