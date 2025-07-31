package com.example.studentcourse.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.studentcourse.dto.CourseDTO;
import com.example.studentcourse.exception.BadRequestException;
import com.example.studentcourse.exception.ResourceNotFoundException;
import com.example.studentcourse.mapper.CourseMapper;
import com.example.studentcourse.model.Course;
import com.example.studentcourse.repository.CourseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.RequiredArgsConstructor;

public interface CourseService {

    CourseDTO createCourse(CourseDTO courseDTO);
    CourseDTO getCourseById(Long id);
    Page<CourseDTO> getAllCourses(Pageable pageable);
    CourseDTO updateCourse(Long id, CourseDTO courseDTO);
    void deleteCourse(Long id);
    List<CourseDTO> getCoursesByDepartment(String department);
    List<CourseDTO> getActiveCourses();
    boolean existsByCode(String code);
}
