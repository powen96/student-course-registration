# Student Course Registration System

A Spring Boot application for managing student course registrations with PostgreSQL.

## Features

- Student management (CRUD operations)
- Course management (CRUD operations)
- Enrollment management
- RESTful API endpoints
- Swagger documentation
- Input validation
- Proper error handling

## Prerequisites

- Java 21
- PostgreSQL
- Maven

## Setup

1. Create a PostgreSQL database:
   ```bash
   createdb student_course_db

2. Initial Data (data.sql)
```bash
-- Insert sample students
INSERT INTO students (name, email, is_active) VALUES
('John Doe', 'john.doe@example.com', true),
('Jane Smith', 'jane.smith@example.com', true),
('Bob Johnson', 'bob.johnson@example.com', false);

 ```bash
-- Insert sample courses
INSERT INTO courses (code, title, description, credit_hours, department, is_active) VALUES
('CS101', 'Introduction to Computer Science', 'Basic computer science concepts', 3, 'Computer Science', true),
('MATH201', 'Calculus I', 'Differential and integral calculus', 4, 'Mathematics', true),
('ENG102', 'English Composition', 'Writing and composition skills', 3, 'English', true);

```bash
-- Insert sample enrollments
INSERT INTO enrollments (student_id, course_id, enrollment_date, grade, active) VALUES
(1, 1, '2023-09-01', 'A', true),
(1, 2, '2023-09-01', 'B+', true),
(2, 1, '2023-09-01', 'A-', true),
(2, 3, '2023-09-01', NULL, true),
(3, 2, '2023-09-01', 'C', false);
