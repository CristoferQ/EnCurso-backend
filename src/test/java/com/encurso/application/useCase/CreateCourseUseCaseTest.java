package com.encurso.application.useCase;

import com.encurso.domain.entity.Course;
import com.encurso.domain.exception.CourseAlreadyExistsException;
import com.encurso.domain.repository.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Create Course Use Case Tests")
class CreateCourseUseCaseTest {

    private CreateCourseUseCase createCourseUseCase;
    private CourseRepository courseRepository;
    private Course course;

    @BeforeEach
    void setUp() {
        courseRepository = mock(CourseRepository.class);
        createCourseUseCase = new CreateCourseUseCase(courseRepository);
        course = new Course("Java Basics", "Learn Java from scratch");
        course.setId(1L);
    }

    @Test
    @DisplayName("Should create course when it does not exist")
    void testCreateCourseSuccessfully() {
        course.setId(null);
        when(courseRepository.findById(null)).thenReturn(Optional.empty());

        assertDoesNotThrow(() -> createCourseUseCase.execute(course));

        verify(courseRepository, times(1)).save(course);
    }

    @Test
    @DisplayName("Should throw CourseAlreadyExistsException when course already exists")
    void testCreateCourseAlreadyExists() {
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));

        assertThrows(CourseAlreadyExistsException.class, () -> createCourseUseCase.execute(course));
        verify(courseRepository, never()).save(course);
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when title is empty")
    void testCreateCourseWithEmptyTitle() {
        course.setTitle("");

        assertThrows(IllegalArgumentException.class, () -> createCourseUseCase.execute(course));
        verify(courseRepository, never()).save(course);
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when title is null")
    void testCreateCourseWithNullTitle() {
        course.setTitle(null);

        assertThrows(IllegalArgumentException.class, () -> createCourseUseCase.execute(course));
        verify(courseRepository, never()).save(course);
    }

    @Test
    @DisplayName("Should save course when title is valid")
    void testCreateCourseWithValidTitle() {
        course.setId(null);
        course.setTitle("Python Advanced");
        when(courseRepository.findById(null)).thenReturn(Optional.empty());

        assertDoesNotThrow(() -> createCourseUseCase.execute(course));

        verify(courseRepository, times(1)).save(course);
    }

    @Test
    @DisplayName("Should create course without id when id is null")
    void testCreateCourseWithoutId() {
        Course newCourse = new Course("Database Design", "Learn SQL and relational databases");
        newCourse.setId(null);

        when(courseRepository.findById(null)).thenReturn(Optional.empty());

        assertDoesNotThrow(() -> createCourseUseCase.execute(newCourse));

        verify(courseRepository, times(1)).save(newCourse);
    }

    @Test
    @DisplayName("Should call save exactly once when creating course")
    void testSaveIsCalledOnce() {
        course.setId(null);
        when(courseRepository.findById(null)).thenReturn(Optional.empty());

        createCourseUseCase.execute(course);

        verify(courseRepository, times(1)).save(course);
    }
}
