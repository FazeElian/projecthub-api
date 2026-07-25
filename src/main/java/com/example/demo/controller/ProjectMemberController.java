package com.example.demo.controller;

import com.example.demo.dto.ProjectMemberRequest;
import com.example.demo.dto.ProjectMemberResponse;
import com.example.demo.service.ProjectMemberService;
import com.example.demo.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/project-members")
public class ProjectMemberController {
    private final ProjectMemberService projectMemberService;
    public ProjectMemberController(ProjectMemberService projectMemberService) {
        this.projectMemberService = projectMemberService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectMemberResponse create(@Valid @RequestBody ProjectMemberRequest request) {
        return projectMemberService.create(request);
    }

    @GetMapping("/by-project/{projectId}")
    public List<ProjectMemberResponse> findByProject(@PathVariable Long projectId) {
        return projectMemberService.findByProject(projectId);
    }

    @GetMapping("/by-student/{studentId}")
    public List<ProjectMemberResponse> findByStudent(@PathVariable Long studentId) {
        return projectMemberService.findByStudent(studentId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        projectMemberService.delete(id);
    }
}