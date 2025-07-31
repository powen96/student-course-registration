# Student Course Registration System - Technical Documentation

## 1. Overview

The Student Course Registration System is a comprehensive web application built with Spring Boot that manages students, courses, and enrollments in an educational institution. The system provides RESTful APIs for creating, reading, updating, and deleting (CRUD) operations on students, courses, and enrollments.

## 2. Technology Stack

- **Backend Framework**: Spring Boot 3.2.0
- **Java Version**: Java 21
- **Database**: PostgreSQL
- **ORM**: Hibernate with JPA
- **API Documentation**: OpenAPI/Swagger
- **Build Tool**: Maven
- **Object Mapping**: MapStruct 1.5.5.Final
- **Validation**: Jakarta Bean Validation
- **Lombok**: For reducing boilerplate code

## 3. System Architecture

The application follows a standard n-tier architecture with the following layers:

- **Controller Layer**: Handles HTTP requests and responses
- **Service Layer**: Contains business logic
- **Repository Layer**: Handles data access
- **Model Layer**: Represents database entities
- **DTO Layer**: Data Transfer Objects for API requests/responses
- **Mapper Layer**: Converts between DTOs and entities
- **Exception Layer**: Handles error cases

## 4. Database Schema

### Tables

1. **students**
   - `id` (PK): Long
   - `name`: String
   - `email`: String (unique)
   - `is_active`: Boolean

2. **courses**
   - `id` (PK): Long
   - `code`: String (unique)
   - `title`: String
   - `description`: String
   - `credit_hours`: Integer
   - `department`: String
   - `is_active`: Boolean

3. **enrollments**
   - `id` (PK): Long
   - `student_id` (FK): Long
   - `course_id` (FK): Long
   - `enrollment_date`: Date
   - `grade`: String
   - `active`: Boolean

## 5. API Endpoints

### Student Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /api/students | Create a new student |
| GET | /api/students | Get all students (paginated) |
| GET | /api/students/{id} | Get student by ID |
| PUT | /api/students/{id} | Update student information |
| DELETE | /api/students/{id} | Delete a student |
| GET | /api/students/active | Get all active students |

### Course Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /api/courses | Create a new course |
| GET | /api/courses | Get all courses (paginated) |
| GET | /api/courses/{id} | Get course by ID |
| PUT | /api/courses/{id} | Update course information |
| DELETE | /api/courses/{id} | Delete a course |
| GET | /api/courses/department/{department} | Get courses by department |
| GET | /api/courses/active | Get all active courses |

### Enrollment Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /api/enrollments | Create a new enrollment |
| GET | /api/enrollments | Get all enrollments (paginated) |
| GET | /api/enrollments/{id} | Get enrollment by ID |
| PUT | /api/enrollments/{id} | Update enrollment information |
| DELETE | /api/enrollments/{id} | Delete an enrollment |
| GET | /api/enrollments/student/{studentId} | Get enrollments by student ID |
| GET | /api/enrollments/course/{courseId} | Get enrollments by course ID |

## 6. Data Transfer Objects (DTOs)

### StudentDTO
- `id`: Long
- `name`: String (required, 2-100 characters)
- `email`: String (required, valid email format)
- `active`: Boolean (default: true)

### CourseDTO
- `id`: Long
- `code`: String (required, 3-10 characters)
- `title`: String (required, max 100 characters)
- `description`: String (max 500 characters)
- `creditHours`: Integer (required, 1-6)
- `department`: String (required)
- `active`: Boolean (default: true)

### EnrollmentDTO
- `id`: Long
- `studentId`: Long (required)
- `courseId`: Long (required)
- `enrollmentDate`: String (read-only)
- `grade`: String (must match A-F with optional +/-, W, or I)
- `active`: Boolean (default: true)
- `studentName`: String (read-only)
- `courseTitle`: String (read-only)
- `courseCode`: String (read-only)

## 7. Validation

The system implements comprehensive validation:

- Required fields are enforced
- String length constraints
- Email format validation
- Numeric range validations
- Custom validation for grades

## 8. Exception Handling

The application has a global exception handler that handles:

- Resource not found exceptions
- Bad request exceptions
- Validation errors
- General server errors

All exceptions return standardized error responses with:
- Timestamp
- Status code
- Error message
- Error details

## 9. API Documentation

The API is documented using OpenAPI/Swagger, available at:
- API Documentation: http://localhost:8081/api-docs
- Swagger UI: http://localhost:8081/swagger-ui

## 10. Object Mapping

MapStruct is used for object mapping between entities and DTOs:

- `StudentMapper`: Maps between `Student` and `StudentDTO`
- `CourseMapper`: Maps between `Course` and `CourseDTO`
- `EnrollmentMapper`: Maps between `Enrollment` and `EnrollmentDTO`

## 11. Configuration

### Application Properties

```properties
# Server
server.port=8081

# PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/student_course_db
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.datasource.driver-class-name=org.postgresql.Driver

# Hikari Connection Pool
spring.datasource.hikari.maximum-pool-size=10
spring.datasource.hikari.connection-timeout=30000

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# OpenAPI/Swagger
springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.path=/swagger-ui
```

## 12. Setup Instructions

1. **Prerequisites**:
   - Java 21+
   - Maven
   - PostgreSQL

2. **Database Setup**:
   - Create a PostgreSQL database named `student_course_db`
   - Configure credentials in `application.properties`

3. **Build and Run**:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

4. **Access the Application**:
   - API Endpoints: http://localhost:8081/api/...
   - Swagger UI: http://localhost:8081/swagger-ui

## 13. Testing

The application includes:
- Unit tests for service layer
- Integration tests for controllers
- Repository tests with test database

Run tests with:
```bash
mvn test
```

## 14. Future Improvements

Potential enhancements for future versions:
1. Add user authentication and authorization
2. Implement course prerequisites
3. Add batch enrollment functionality
4. Create reporting features
5. Add email notifications
6. Implement a front-end UI
7. Add file upload for student photos and course materials

---

*Documentation created: July 31, 2025*
