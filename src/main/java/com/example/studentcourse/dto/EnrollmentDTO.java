package com.example.studentcourse.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Schema(description = "Enrollment Data Transfer Object")
public class EnrollmentDTO {
    @Schema(description = "Enrollment ID", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotNull(message = "Student ID is required")
    @Schema(description = "ID of the enrolled student", example = "1", required = true)
    private Long studentId;

    @NotNull(message = "Course ID is required")
    @Schema(description = "ID of the course being enrolled in", example = "1", required = true)
    private Long courseId;

    @Schema(description = "Date of enrollment", example = "2023-09-01", accessMode = Schema.AccessMode.READ_ONLY)
    private String enrollmentDate;

    @Pattern(regexp = "^[A-F][+-]?$|^W$|^I$", message = "Invalid grade format")
    @Schema(description = "Grade received (A-F with optional +/-, W for withdrawal, I for incomplete)",
            example = "A", allowableValues = {"A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "F", "W", "I"})
    private String grade;

    @Schema(description = "Whether the enrollment is currently active", example = "true")
    private Boolean active = true;

    // Read-only fields
    @Schema(description = "Student name (read-only)", accessMode = Schema.AccessMode.READ_ONLY)
    private String studentName;

    @Schema(description = "Course title (read-only)", accessMode = Schema.AccessMode.READ_ONLY)
    private String courseTitle;

    @Schema(description = "Course code (read-only)", accessMode = Schema.AccessMode.READ_ONLY)
    private String courseCode;
}