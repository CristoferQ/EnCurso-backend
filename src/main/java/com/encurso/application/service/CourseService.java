package com.encurso.application.service;

import com.encurso.domain.entity.Course;
import com.encurso.domain.exception.ValidationException;

public class CourseService {
    public String createCourse(Course courseRequest) {
        validateCourse(courseRequest);
        return "Course created successfully";
    }

    private void validateCourse(Course course) {
        if (course == null) {
            throw new ValidationException("Course must not be null.");
        }
        if (course.getTitle() == null || course.getTitle().isBlank()) {
            throw new ValidationException("Course title is required.");
        }
        if (course.getDescription() == null || course.getDescription().isBlank()) {
            throw new ValidationException("Course description is required.");
        }
        if (course.getTitle().length() > 100) {
            throw new ValidationException("Course title cannot exceed 100 characters.");
        }
    }
}
