package com.encurso.domain.repository;

import com.encurso.domain.entity.Course;

import java.util.Optional;

public interface CourseRepository {
    void save(Course course);
    Optional<Course> findById(Long id);
}
