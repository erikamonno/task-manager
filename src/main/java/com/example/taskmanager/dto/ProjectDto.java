package com.example.taskmanager.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class ProjectDto {

    private UUID id;

    @NotBlank
    private String name;

    private String description;

    private Instant createdTimestamp;
}
