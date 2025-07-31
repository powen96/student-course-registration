package com.example.studentcourse.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Schema(description = "Student Data Transfer Object")
public class StudentDTO {
    @Schema(description = "Student ID", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    @Schema(description = "Student's full name", example = "John Doe", required = true)
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Schema(description = "Student's email address", example = "john.doe@example.com", required = true)
    private String email;

    @Schema(description = "Whether the student is active", example = "true")
    private Boolean active = true;
}