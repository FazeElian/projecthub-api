package com.example.demo.service;

import com.example.demo.dto.StudentRequest;
import com.example.demo.dto.StudentResponse;
import com.example.demo.entity.Student;
import com.example.demo.exception.DuplicateResourceException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class StudentService {
    private final StudentRepository studentRepository;
    public StudentResponse create(StudentRequest req) {
        if(studentRepository.existsByEmail((req.email()))) {
            throw new DuplicateResourceException("Ya existe un estudiante con ese correo electrónico.");
        }
        Student student = req.toEntity();
        return StudentResponse.from(studentRepository.save(student));
    }

    @Transactional()
    public List<StudentResponse> findAll() {
        return studentRepository.findAll().stream()
                .map(StudentResponse::from)
                .toList();
    }

    private Student getOrThrow(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estudiante no encontrado"));
    }

    @Transactional()
    public StudentResponse findById(Long id) {
        Student student = getOrThrow(id);
        return StudentResponse.from(student);
    }

    public StudentResponse update(Long id, StudentRequest request) {
        Student student = getOrThrow(id);
        student.setFullName(request.fullName());
        student.setEmail(request.email());
        student.setCareer(request.career());
        student.setSemester(request.semester());
        return StudentResponse.from(studentRepository.save(student));
    }

    public void delete(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Estudiante no encontrado");
        }
        studentRepository.deleteById(id);
    }
}
