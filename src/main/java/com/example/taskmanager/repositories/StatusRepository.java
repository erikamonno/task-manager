package com.example.taskmanager.repositories;

import com.example.taskmanager.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.UUID;

public interface StatusRepository extends JpaRepository<Status, UUID>, JpaSpecificationExecutor<Status> {

    Optional<Status> findByName(String name);
}
