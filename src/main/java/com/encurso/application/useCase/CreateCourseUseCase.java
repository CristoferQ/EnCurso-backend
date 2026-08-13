package com.encurso.application.useCase;

import com.encurso.domain.entity.Course;
import com.encurso.domain.exception.CourseAlreadyExistsException;
import com.encurso.domain.repository.CourseRepository;

public class CreateCourseUseCase {
    private final CourseRepository courseRepository;

    public CreateCourseUseCase(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public void execute(Course course) {
        if (course.getId() != null && courseRepository.findById(course.getId()).isPresent()) {
            throw new CourseAlreadyExistsException("El curso con ID " + course.getId() + " ya existe.");
        }

        if (course.getTitle() == null || course.getTitle().isEmpty()) {
            throw new IllegalArgumentException("El título del curso no puede estar vacío.");
        }

        courseRepository.save(course);
    }
}
