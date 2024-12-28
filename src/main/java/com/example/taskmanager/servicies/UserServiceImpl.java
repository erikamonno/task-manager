package com.example.taskmanager.servicies;

import com.example.taskmanager.dto.UserDto;
import com.example.taskmanager.entities.User;
import com.example.taskmanager.exceptions.UserNotFounfException;
import com.example.taskmanager.filters.UserFilter;
import com.example.taskmanager.mappers.UserMapper;
import com.example.taskmanager.repositories.UserRepository;
import com.example.taskmanager.specifications.UserSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    public UserServiceImpl(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public UserDto insertUser(UserDto dto) {
        User entity = new User();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setUsername(dto.getUsername());
        entity.setEmail(dto.getEmail());
        entity.setBirthDate(dto.getBirthDate());
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    @Override
    public UserDto readOneUser(UUID id) {
        var oEntity = repository.findById(id);
        if(oEntity.isEmpty()) {
            throw new UserNotFounfException("User not found");
        }
        var entity = oEntity.get();
        return mapper.toDto(entity);
    }

    @Override
    public Page<UserDto> searchUser(Pageable pageable, UserFilter filter) {
        return repository.findAll(new UserSpecification(filter), pageable).map(user -> mapper.toDto(user));
    }

    @Override
    @Transactional
    public void updateUser(UserDto dto, UUID id) {
        var oEntity = repository.findById(id);
        if(oEntity.isEmpty()) {
            throw new UserNotFounfException("User not found");
        }
        var entity = oEntity.get();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setUsername(dto.getUsername());
        entity.setEmail(dto.getEmail());
        entity.setBirthDate(dto.getBirthDate());
    }

    @Override
    public void deleteUser(UUID id) {
        repository.deleteById(id);
    }
}
