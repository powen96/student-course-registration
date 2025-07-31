package com.example.studentcourse.service;

import com.example.studentcourse.dto.EnrollmentDTO;

import java.util.List;

public interface EnrollmentService {
    EnrollmentDTO createEnrollment(EnrollmentDTO enrollmentDTO);
    EnrollmentDTO getEnrollmentById(Long id);
    List<EnrollmentDTO> getAllEnrollments();
    EnrollmentDTO updateEnrollment(Long id, EnrollmentDTO enrollmentDTO);
    void deleteEnrollment(Long id);
    List<EnrollmentDTO> getEnrollmentsByStudent(Long studentId);
    List<EnrollmentDTO> getEnrollmentsByCourse(Long courseId);
    List<EnrollmentDTO> getActiveEnrollments();
}