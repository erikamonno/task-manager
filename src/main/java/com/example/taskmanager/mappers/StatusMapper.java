package com.example.taskmanager.mappers;

import com.example.taskmanager.dto.StatusDto;
import com.example.taskmanager.entities.Status;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring")

public interface StatusMapper {

    StatusDto toDto(Status status);

    Status toEntity(StatusDto dto);
}
