package com.example.taskmanager.filters;

import lombok.Data;

import java.time.Instant;

@Data
public class ProjectFilter {
    private String name;

    private String description;

    private Instant createdTimestamp;
}
