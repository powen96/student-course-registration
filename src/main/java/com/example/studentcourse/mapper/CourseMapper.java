package com.example.studentcourse.mapper;

import com.example.studentcourse.dto.CourseDTO;
import com.example.studentcourse.model.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    CourseDTO toCourseDTO(Course course);

    @Mapping(target = "students", ignore = true)
    Course toCourse(CourseDTO courseDTO);
}