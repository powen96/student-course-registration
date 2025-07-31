package com.example.studentcourse.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "courses")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private String title;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private Integer creditHours;

    @Column(nullable = false)
    private String department;

    @Column(name = "is_active", nullable = false)
    private Boolean active = true;

    @ManyToMany(mappedBy = "courses")
    private Set<Student> students = new HashSet<>();
}