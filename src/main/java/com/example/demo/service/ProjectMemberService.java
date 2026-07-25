package com.example.demo.service;

import com.example.demo.dto.ProjectMemberRequest;
import com.example.demo.dto.ProjectMemberResponse;
import com.example.demo.entity.Project;
import com.example.demo.entity.ProjectMember;
import com.example.demo.entity.Student;
import com.example.demo.exception.DuplicateResourceException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ProjectMemberRepository;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjectMemberService {

    private final ProjectMemberRepository projectMemberRepository;
    private final ProjectRepository projectRepository;
    private final StudentRepository studentRepository;

    public ProjectMemberResponse create(ProjectMemberRequest request) {
        if (projectMemberRepository.existsByProjectIdAndStudentId(request.projectId(), request.studentId())) {
            throw new DuplicateResourceException("El estudiante ya pertenece a este proyecto");
        }

        Project project = projectRepository.findById(request.projectId())
                .orElseThrow(() -> new ResourceNotFoundException("Proyecto no encontrado: " + request.projectId()));

        Student student = studentRepository.findById(request.studentId())
                .orElseThrow(() -> new ResourceNotFoundException("Estudiante no encontrado: " + request.studentId()));

        ProjectMember member = request.toEntity(project, student);
        return ProjectMemberResponse.from(projectMemberRepository.save(member));
    }

    @Transactional(readOnly = true)
    public List<ProjectMemberResponse> findByProject(Long projectId) {
        return projectMemberRepository.findByProjectId(projectId).stream()
                .map(ProjectMemberResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ProjectMemberResponse> findByStudent(Long studentId) {
        return projectMemberRepository.findByStudentId(studentId).stream()
                .map(ProjectMemberResponse::from)
                .toList();
    }

    public void delete(Long id) {
        if (!projectMemberRepository.existsById(id)) {
            throw new ResourceNotFoundException("Miembro no encontrado: " + id);
        }
        projectMemberRepository.deleteById(id);
    }
}