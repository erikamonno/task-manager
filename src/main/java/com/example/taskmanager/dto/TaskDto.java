package com.example.taskmanager.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class TaskDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID id;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Instant createdTimestamp;

    private Instant startedTimestamp;

    private Instant endTimestamp;

    @NotNull
    private ProjectDto project;

    private StatusDto status;

    private UserDto assignedTo;

    private UserDto createdBy;
}
