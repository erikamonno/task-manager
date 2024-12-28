package com.example.taskmanager.filters;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class TaskFilter {
    private String title;
    private String description;
    private Instant createdTimestampFrom;
    private Instant createdTimestampTo;
    private Instant startedTimestampFrom;
    private Instant startedTimestampTo;
    private Instant endTimestampFrom;
    private Instant endTimestampTo;
    private UUID projectId;
    private UUID statusId;
    private UUID assignedTo;
    private UUID createdBy;
}
