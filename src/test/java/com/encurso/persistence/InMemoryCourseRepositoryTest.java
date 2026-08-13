package com.encurso.persistence;

import com.encurso.domain.entity.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("In Memory Course Repository Tests")
class InMemoryCourseRepositoryTest {

    private InMemoryCourseRepository repository;
    private Course course;

    @BeforeEach
    void setUp() {
        repository = new InMemoryCourseRepository();
        course = new Course("Java Basics", "Learn Java from scratch");
        course.setId(1L);
    }

    @Test
    @DisplayName("Should save course successfully")
    void testSaveCourseSuccessfully() {
        repository.save(course);

        Optional<Course> result = repository.findById(1L);
        assertTrue(result.isPresent());
        assertEquals("Java Basics", result.get().getTitle());
    }

    @Test
    @DisplayName("Should not save course with null id")
    void testSaveCourseWithNullId() {
        course.setId(null);
        repository.save(course);

        Optional<Course> result = repository.findById(1L);
        assertFalse(result.isPresent());
    }

    @Test
    @DisplayName("Should not save null course")
    void testSaveNullCourse() {
        assertDoesNotThrow(() -> repository.save(null));

        Optional<Course> result = repository.findById(1L);
        assertFalse(result.isPresent());
    }

    @Test
    @DisplayName("Should find course by id")
    void testFindCourseById() {
        repository.save(course);

        Optional<Course> result = repository.findById(1L);
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        assertEquals("Java Basics", result.get().getTitle());
        assertEquals("Learn Java from scratch", result.get().getDescription());
    }

    @Test
    @DisplayName("Should return empty optional when course not found")
    void testFindCourseNotFound() {
        Optional<Course> result = repository.findById(999L);
        assertFalse(result.isPresent());
    }

    @Test
    @DisplayName("Should update course when saving with same id")
    void testUpdateCourseWhenSavingWithSameId() {
        repository.save(course);

        Course updatedCourse = new Course("Advanced Java", "Learn advanced Java concepts");
        updatedCourse.setId(1L);
        repository.save(updatedCourse);

        Optional<Course> result = repository.findById(1L);
        assertTrue(result.isPresent());
        assertEquals("Advanced Java", result.get().getTitle());
        assertEquals("Learn advanced Java concepts", result.get().getDescription());
    }

    @Test
    @DisplayName("Should save multiple courses")
    void testSaveMultipleCourses() {
        Course course2 = new Course("Python Basics", "Learn Python from scratch");
        course2.setId(2L);
        Course course3 = new Course("JavaScript", "Learn JavaScript for web development");
        course3.setId(3L);

        repository.save(course);
        repository.save(course2);
        repository.save(course3);

        assertTrue(repository.findById(1L).isPresent());
        assertTrue(repository.findById(2L).isPresent());
        assertTrue(repository.findById(3L).isPresent());
    }

    @Test
    @DisplayName("Should preserve course data after save")
    void testPreserveCourseDataAfterSave() {
        repository.save(course);

        Optional<Course> result = repository.findById(1L);
        assertTrue(result.isPresent());
        assertEquals("Java Basics", result.get().getTitle());
        assertEquals("Learn Java from scratch", result.get().getDescription());
        assertTrue(result.get().getEnrollments().isEmpty());
    }
}
