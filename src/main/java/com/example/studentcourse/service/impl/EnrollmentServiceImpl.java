package com.example.studentcourse.service.impl;

import com.example.studentcourse.dto.EnrollmentDTO;
import com.example.studentcourse.exception.ResourceNotFoundException;
import com.example.studentcourse.mapper.EnrollmentMapper;
import com.example.studentcourse.model.*;
import com.example.studentcourse.repository.CourseRepository;
import com.example.studentcourse.repository.EnrollmentRepository;
import com.example.studentcourse.repository.StudentRepository;
import com.example.studentcourse.service.EnrollmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final EnrollmentMapper enrollmentMapper;

    public EnrollmentServiceImpl(EnrollmentRepository enrollmentRepository,
                                 StudentRepository studentRepository,
                                 CourseRepository courseRepository,
                                 EnrollmentMapper enrollmentMapper) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.enrollmentMapper = enrollmentMapper;
    }

    @Override
    public EnrollmentDTO createEnrollment(EnrollmentDTO enrollmentDTO) {
        Student student = studentRepository.findById(enrollmentDTO.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", enrollmentDTO.getStudentId()));

        Course course = courseRepository.findById(enrollmentDTO.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course", "id", enrollmentDTO.getCourseId()));

        if (enrollmentRepository.existsByStudentAndCourse(student, course)) {
            throw new IllegalArgumentException("Student is already enrolled in this course");
        }

        Enrollment enrollment = enrollmentMapper.toEnrollment(enrollmentDTO);
        enrollment.setStudent(student);
        enrollment.setCourse(course);

        Enrollment savedEnrollment = enrollmentRepository.save(enrollment);
        return enrollmentMapper.toEnrollmentDTO(savedEnrollment);
    }

    @Override
    @Transactional(readOnly = true)
    public EnrollmentDTO getEnrollmentById(Long id) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment", "id", id));
        return enrollmentMapper.toEnrollmentDTO(enrollment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EnrollmentDTO> getAllEnrollments() {
        return enrollmentRepository.findAll().stream()
                .map(enrollmentMapper::toEnrollmentDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EnrollmentDTO updateEnrollment(Long id, EnrollmentDTO enrollmentDTO) {
        Enrollment existingEnrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment", "id", id));

        if (enrollmentDTO.getGrade() != null) {
            existingEnrollment.setGrade(enrollmentDTO.getGrade());
        }
        if (enrollmentDTO.getActive() != null) {
            existingEnrollment.setActive(enrollmentDTO.getActive());
        }

        Enrollment updatedEnrollment = enrollmentRepository.save(existingEnrollment);
        return enrollmentMapper.toEnrollmentDTO(updatedEnrollment);
    }

    @Override
    public void deleteEnrollment(Long id) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment", "id", id));
        enrollmentRepository.delete(enrollment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EnrollmentDTO> getEnrollmentsByStudent(Long studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new ResourceNotFoundException("Student", "id", studentId);
        }
        return enrollmentRepository.findByStudentId(studentId).stream()
                .map(enrollmentMapper::toEnrollmentDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<EnrollmentDTO> getEnrollmentsByCourse(Long courseId) {
        if (!courseRepository.existsById(courseId)) {
            throw new ResourceNotFoundException("Course", "id", courseId);
        }
        return enrollmentRepository.findByCourseId(courseId).stream()
                .map(enrollmentMapper::toEnrollmentDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<EnrollmentDTO> getActiveEnrollments() {
        return enrollmentRepository.findByActiveTrue().stream()
                .map(enrollmentMapper::toEnrollmentDTO)
                .collect(Collectors.toList());
    }
}