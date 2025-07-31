package com.example.studentcourse.service.impl;

import com.example.studentcourse.dto.StudentDTO;
import com.example.studentcourse.exception.ResourceNotFoundException;
import com.example.studentcourse.mapper.StudentMapper;
import com.example.studentcourse.model.Student;
import com.example.studentcourse.repository.StudentRepository;
import com.example.studentcourse.service.StudentService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;


    @Override
    public StudentDTO createStudent(StudentDTO studentDTO) {
        if (studentRepository.existsByEmail(studentDTO.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        Student student = studentMapper.toStudent(studentDTO);
        Student savedStudent = studentRepository.save(student);
        return studentMapper.toStudentDTO(savedStudent);
    }

    @Override
    @Transactional(readOnly = true)
    public StudentDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", id));
        return studentMapper.toStudentDTO(student);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(studentMapper::toStudentDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDTO updateStudent(Long id, StudentDTO studentDTO) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", id));

        if (studentDTO.getName() != null) {
            existingStudent.setName(studentDTO.getName());
        }
        if (studentDTO.getEmail() != null && !studentDTO.getEmail().equals(existingStudent.getEmail())) {
            if (studentRepository.existsByEmail(studentDTO.getEmail())) {
                throw new IllegalArgumentException("Email already exists");
            }
            existingStudent.setEmail(studentDTO.getEmail());
        }
        if (studentDTO.getActive() != null) {
            existingStudent.setActive(studentDTO.getActive());
        }

        Student updatedStudent = studentRepository.save(existingStudent);
        return studentMapper.toStudentDTO(updatedStudent);
    }

    @Override
    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", id));
        studentRepository.delete(student);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentDTO> getActiveStudents() {
        return studentRepository.findByActiveTrue().stream()
                .map(studentMapper::toStudentDTO)
                .collect(Collectors.toList());
    }
}