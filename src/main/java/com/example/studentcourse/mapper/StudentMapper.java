package com.example.studentcourse.mapper;

import com.example.studentcourse.dto.StudentDTO;
import com.example.studentcourse.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    StudentDTO toStudentDTO(Student student);

    @Mapping(target = "courses", ignore = true)
    Student toStudent(StudentDTO studentDTO);
}