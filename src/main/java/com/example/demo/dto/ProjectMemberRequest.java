package com.example.demo.dto;

import com.example.demo.entity.Project;
import com.example.demo.entity.ProjectMember;
import com.example.demo.entity.ProjectRole;
import com.example.demo.entity.Student;
import jakarta.validation.constraints.NotNull;

public record ProjectMemberRequest(
        @NotNull Long projectId,
        @NotNull Long studentId,
        @NotNull ProjectRole role
) {
    public ProjectMember toEntity(Project project, Student student) {
        ProjectMember member = new ProjectMember();
        member.setProject(project);
        member.setStudent(student);
        member.setRole(role);
        return member;
    }
}