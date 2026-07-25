package com.example.demo.dto;

import com.example.demo.entity.Project;
import com.example.demo.entity.ProjectStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record ProjectRequest (
    @NotBlank @Size(min = 5, max = 60) String name,
    @Size(max = 500) String description,
    @Size(max = 255) String repositoryUrl,
    @Enumerated(EnumType.STRING) ProjectStatus status,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
    public Project toEntity() {
        Project project = new Project();
        project.setName(name);
        project.setDescription(description);
        project.setRepositoryUrl(repositoryUrl);
        project.setStatus(status);
        project.setCreatedAt(createdAt);
        project.setUpdatedAt(updatedAt);

        return project;
    }
}
