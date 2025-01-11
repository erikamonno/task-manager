package com.example.taskmanager.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;
@Data
@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "created_timestamp")
    @CreationTimestamp
    private Instant createdTimestamp;

    @Column(name = "started_timestamp")
    @CreationTimestamp
    private Instant startedTimestamp;

    @Column(name = "end_timestamp")
    @CreationTimestamp
    private Instant endTimestamp;

    @Column(name = "estimated_time", columnDefinition = "interval")
    private Duration estimatedTime;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "assigned_to")
    private User assignedTo;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;
}
