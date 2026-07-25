package com.example.demo.controller;

import com.example.demo.dto.ProjectRequest;
import com.example.demo.dto.ProjectResponse;
import com.example.demo.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {
    private final ProjectService projectService;
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public List<ProjectResponse> findAll() { return projectService.findAll(); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectResponse create(@Valid @RequestBody ProjectRequest req) {
        return projectService.create(req);
    }

    @GetMapping("/{id}")
    public ProjectResponse findById(@PathVariable Long id) {
        return projectService.findById(id);
    }

    @PutMapping("/{id}")
    public ProjectResponse update(Long id, ProjectRequest req) {
        return projectService.update(id, req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { projectService.delete(id); }
}
