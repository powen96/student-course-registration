package com.example.studentcourse.repository;

import com.example.studentcourse.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByDepartment(String department);
    List<Course> findByActiveTrue();
    List<Course> findByDepartmentAndActive(String department, Boolean active);
    boolean existsByCode(String code);
}