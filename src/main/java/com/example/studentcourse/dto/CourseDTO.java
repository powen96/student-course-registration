package com.example.studentcourse.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Schema(description = "Course Data Transfer Object")
public class CourseDTO {
    @Schema(description = "Course ID", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotBlank(message = "Course code is required")
    @Size(min = 3, max = 10, message = "Code must be 3-10 characters")
    @Schema(description = "Unique course code", example = "CS101", required = true)
    private String code;

    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title cannot exceed 100 characters")
    @Schema(description = "Course title", example = "Introduction to Computer Science", required = true)
    private String title;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    @Schema(description = "Course description", example = "Fundamentals of programming and algorithms")
    private String description;

    @NotNull(message = "Credit hours are required")
    @Min(value = 1, message = "Minimum 1 credit hour")
    @Max(value = 6, message = "Maximum 6 credit hours")
    @Schema(description = "Credit hours for the course", example = "3", required = true)
    private Integer creditHours;

    @NotBlank(message = "Department is required")
    @Schema(description = "Department offering the course", example = "Computer Science", required = true)
    private String department;

    @Schema(description = "Whether the course is currently active", example = "true")
    private Boolean active = true;
}