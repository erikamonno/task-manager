package com.example.taskmanager.mappers;

import com.example.taskmanager.dto.AttachmentDto;
import com.example.taskmanager.entities.Attachment;
import lombok.Data;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AttachmentMapper {

    AttachmentDto toDto(Attachment entity);
    Attachment toEntity(AttachmentDto dto);
}
