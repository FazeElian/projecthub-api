package com.example.demo.service;

import com.example.demo.dto.ProjectRequest;
import com.example.demo.dto.ProjectResponse;
import com.example.demo.entity.Project;
import com.example.demo.exception.DuplicateResourceException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ProjectRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjectService {
    private final ProjectRepository projectRepository;
    public ProjectResponse create(ProjectRequest req) {
        if (projectRepository.existsByName(req.name())) {
            throw new DuplicateResourceException("Ya existe un projecto con ese nombre.");
        }
        Project project = req.toEntity();
        return ProjectResponse.from(projectRepository.save(project));
    }

    @Transactional()
    public List<ProjectResponse> findAll() {
        return projectRepository.findAll().stream()
                .map(ProjectResponse::from)
                .toList();
    }

    private Project getOrThrow(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proyecto no encontrado"));
    }

    @Transactional()
    public ProjectResponse findById(Long id) {
        Project project = getOrThrow(id);
        return ProjectResponse.from(project);
    }

    public ProjectResponse update(Long id, ProjectRequest request) {
        Project project = getOrThrow(id);
        project.setName(request.name());
        project.setDescription(request.description());
        project.setRepositoryUrl(request.repositoryUrl());
        project.setStatus(request.status());
        project.setCreatedAt(request.createdAt());
        project.setUpdatedAt(request.updatedAt());

        return ProjectResponse.from(projectRepository.save(project));
    }

    public void delete(Long id) {
        if (!projectRepository.existsById(id)) {
            throw new ResourceNotFoundException("Proyecto no encontrado");
        }
        projectRepository.deleteById(id);
    }
}
