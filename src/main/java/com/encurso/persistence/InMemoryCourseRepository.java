package com.encurso.persistence;

import com.encurso.domain.entity.Course;
import com.encurso.domain.repository.CourseRepository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

        public class InMemoryCourseRepository implements CourseRepository {

    private final Map<Long, Course> storage = new ConcurrentHashMap<>();

    @Override
    public void save(Course course) {
        if (course != null && course.getId() != null) {
            storage.put(course.getId(), course);
        }
    }

    @Override
    public Optional<Course> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }
}
