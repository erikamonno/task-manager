package com.example.taskmanager.mappers;

import com.example.taskmanager.dto.TaskDto;
import com.example.taskmanager.entities.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {StatusMapper.class, UserMapper.class, ProjectMapper.class})
public interface TaskMapper {
    //Mapstruct funziona andando a comparare i tipi tra i due oggetti(input e output del mapper e quindi entity e dto)
    //Confronta il tipo di dato e il nome dell'attributo, se sono uguali fa il travaso

    TaskDto toDto (Task entity);
    Task toEntity (TaskDto dto);
}
