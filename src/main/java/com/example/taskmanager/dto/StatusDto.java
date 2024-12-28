package com.example.taskmanager.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data
public class StatusDto {

    private UUID id;

    @NotBlank
    private String name;

    private String description;
}
