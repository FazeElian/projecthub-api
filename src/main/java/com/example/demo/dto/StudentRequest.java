package com.example.demo.dto;
import com.example.demo.entity.Student;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record StudentRequest(
        @NotBlank @Size(min = 3, max = 100) String fullName,
        @NotBlank @Email @Size(max = 150) String email,
        @NotBlank @Size(min = 3, max = 100) String career,
        @NotNull Integer semester
) {
    public Student toEntity() {
        Student student = new Student();
        student.setFullName(fullName);
        student.setEmail(email);
        student.setCareer(career);
        student.setSemester(semester);
        return student;
    }
}