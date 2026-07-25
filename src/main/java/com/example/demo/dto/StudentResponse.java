package com.example.demo.dto;

import com.example.demo.entity.Student;

import java.time.LocalDateTime;

public record StudentResponse (
    Long id,
    String fullName,
    String email,
    String career,
    Integer semester,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
    public static StudentResponse from (Student student) {
        return new StudentResponse(
                student.getId(),
                student.getFullName(),
                student.getEmail(),
                student.getCareer(),
                student.getSemester(),
                student.getCreatedAt(),
                student.getUpdatedAt()
        );
    }
}
