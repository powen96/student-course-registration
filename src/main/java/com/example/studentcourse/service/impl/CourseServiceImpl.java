package com.example.studentcourse.service.impl;

import com.example.studentcourse.dto.CourseDTO;
import com.example.studentcourse.exception.ResourceNotFoundException;
import com.example.studentcourse.mapper.CourseMapper;
import com.example.studentcourse.model.Course;
import com.example.studentcourse.repository.CourseRepository;
import com.example.studentcourse.service.CourseService;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    @Override
    public CourseDTO createCourse(CourseDTO courseDTO) {
        if (courseRepository.existsByCode(courseDTO.getCode())) {
            throw new IllegalArgumentException("Course code already exists");
        }

        Course course = courseMapper.toCourse(courseDTO);
        Course savedCourse = courseRepository.save(course);
        return courseMapper.toCourseDTO(savedCourse);
    }

    @Override
    @Transactional(readOnly = true)
    public CourseDTO getCourseById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course", "id", id));
        return courseMapper.toCourseDTO(course);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CourseDTO> getAllCourses(Pageable pageable) {
        return courseRepository.findAll(pageable)
                .map(courseMapper::toCourseDTO);
    }

    @Override
    public CourseDTO updateCourse(Long id, CourseDTO courseDTO) {
        Course existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course", "id", id));

        if (courseDTO.getTitle() != null) {
            existingCourse.setTitle(courseDTO.getTitle());
        }
        if (courseDTO.getDescription() != null) {
            existingCourse.setDescription(courseDTO.getDescription());
        }
        if (courseDTO.getCreditHours() != null) {
            existingCourse.setCreditHours(courseDTO.getCreditHours());
        }
        if (courseDTO.getDepartment() != null) {
            existingCourse.setDepartment(courseDTO.getDepartment());
        }
        if (courseDTO.getActive() != null) {
            existingCourse.setActive(courseDTO.getActive());
        }

        // Prevent code changes
        if (courseDTO.getCode() != null && !courseDTO.getCode().equals(existingCourse.getCode())) {
            throw new IllegalArgumentException("Course code cannot be changed");
        }

        Course updatedCourse = courseRepository.save(existingCourse);
        return courseMapper.toCourseDTO(updatedCourse);
    }

    @Override
    public void deleteCourse(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course", "id", id));
        courseRepository.delete(course);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseDTO> getCoursesByDepartment(String department) {
        return courseRepository.findByDepartment(department).stream()
                .map(courseMapper::toCourseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseDTO> getActiveCourses() {
        return courseRepository.findByActiveTrue().stream()
                .map(courseMapper::toCourseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByCode(String code) {
        return courseRepository.existsByCode(code);
    }
}