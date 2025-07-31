package com.example.studentcourse.mapper;

import com.example.studentcourse.dto.EnrollmentDTO;
import com.example.studentcourse.model.Enrollment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {StudentMapper.class, CourseMapper.class})
public interface EnrollmentMapper {

    @Mapping(source = "student.id", target = "studentId")
    @Mapping(source = "student.name", target = "studentName")
    @Mapping(source = "course.id", target = "courseId")
    @Mapping(source = "course.title", target = "courseTitle")
    @Mapping(source = "course.code", target = "courseCode")
    @Mapping(source = "enrollmentDate", target = "enrollmentDate", dateFormat = "yyyy-MM-dd")
    EnrollmentDTO toEnrollmentDTO(Enrollment enrollment);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enrollmentDate", ignore = true)
    @Mapping(target = "student", ignore = true)
    @Mapping(target = "course", ignore = true)
    Enrollment toEnrollment(EnrollmentDTO enrollmentDTO);
}