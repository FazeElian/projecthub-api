package com.example.demo.dto;

import com.example.demo.entity.ProjectMember;
import com.example.demo.entity.ProjectRole;

import java.time.LocalDateTime;

public record ProjectMemberResponse(
        Long id,
        Long projectId,
        String projectName,
        Long studentId,
        String studentName,
        ProjectRole role,
        LocalDateTime joinedAt
) {
    public static ProjectMemberResponse from(ProjectMember member) {
        return new ProjectMemberResponse(
                member.getId(),
                member.getProject().getId(),
                member.getProject().getName(),
                member.getStudent().getId(),
                member.getStudent().getFullName(),
                member.getRole(),
                member.getJoinedAt()
        );
    }
}