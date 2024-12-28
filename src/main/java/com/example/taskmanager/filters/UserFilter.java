package com.example.taskmanager.filters;

import jakarta.validation.constraints.Email;
import lombok.Data;

import java.time.Instant;

@Data
public class UserFilter {
    private String name;

    private String surname;

    private String description;

    @Email
    private String email;

    private Instant birthDate;
}
