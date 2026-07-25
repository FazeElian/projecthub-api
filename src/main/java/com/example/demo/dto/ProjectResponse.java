package com.example.demo.dto;

import com.example.demo.entity.Project;
import com.example.demo.entity.ProjectStatus;

import java.time.LocalDateTime;

public record ProjectResponse (
    Long id,
    String name,
    String description,
    String repositoryUrl,
    ProjectStatus status,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
    public static ProjectResponse from (Project project) {
        return new ProjectResponse (
            project.getId(),
            project.getName(),
            project.getDescription(),
            project.getRepositoryUrl(),
            project.getStatus(),
            project.getCreatedAt(),
            project.getUpdatedAt()
        );
    }
}