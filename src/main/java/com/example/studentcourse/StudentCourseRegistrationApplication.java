package com.example.studentcourse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class StudentCourseRegistrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentCourseRegistrationApplication.class, args);
	}

}
