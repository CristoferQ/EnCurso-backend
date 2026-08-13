package com.encurso.application.service;

import com.encurso.domain.entity.Course;
import com.encurso.domain.exception.ValidationException;
import com.encurso.application.service.CourseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("CourseService Tests")
class CourseServiceTest {

    private final CourseService courseService = new CourseService();

    @Test
    @DisplayName("Should validate null course")
    void testCreateCourseWithNullCourse() {
        assertThrows(ValidationException.class, () -> courseService.createCourse(null));
    }

    @Test
    @DisplayName("Should validate empty title")
    void testCreateCourseWithEmptyTitle() {
        Course course = new Course();
        course.setTitle("");
        course.setDescription("A valid description");

        assertThrows(ValidationException.class, () -> courseService.createCourse(course));
    }

    @Test
    @DisplayName("Should validate null title")
    void testCreateCourseWithNullTitle() {
        Course course = new Course();
        course.setTitle(null);
        course.setDescription("A valid description");

        assertThrows(ValidationException.class, () -> courseService.createCourse(course));
    }

    @Test
    @DisplayName("Should validate empty description")
    void testCreateCourseWithEmptyDescription() {
        Course course = new Course();
        course.setTitle("Java Basics");
        course.setDescription("");

        assertThrows(ValidationException.class, () -> courseService.createCourse(course));
    }

    @Test
    @DisplayName("Should validate null description")
    void testCreateCourseWithNullDescription() {
        Course course = new Course();
        course.setTitle("Java Basics");
        course.setDescription(null);

        assertThrows(ValidationException.class, () -> courseService.createCourse(course));
    }

    @Test
    @DisplayName("Should validate title exceeds max length")
    void testCreateCourseWithTitleExceedsMaxLength() {
        Course course = new Course();
        course.setTitle("a".repeat(101));
        course.setDescription("A valid description");

        assertThrows(ValidationException.class, () -> courseService.createCourse(course));
    }

    @Test
    @DisplayName("Should accept valid course data")
    void testCreateCourseWithValidData() {
        Course course = new Course();
        course.setTitle("Java Basics");
        course.setDescription("Learn Java fundamentals");

        String result = courseService.createCourse(course);
        assertEquals("Course created successfully", result);
    }

    @Test
    @DisplayName("Should accept title with exactly 100 characters")
    void testCreateCourseWithMaxLengthTitle() {
        Course course = new Course();
        course.setTitle("a".repeat(100));
        course.setDescription("A valid description");

        String result = courseService.createCourse(course);
        assertEquals("Course created successfully", result);
    }

    @Test
    @DisplayName("Should validate blank title (whitespace)")
    void testCreateCourseWithBlankTitle() {
        Course course = new Course();
        course.setTitle("   ");
        course.setDescription("A valid description");

        assertThrows(ValidationException.class, () -> courseService.createCourse(course));
    }

    @Test
    @DisplayName("Should validate blank description (whitespace)")
    void testCreateCourseWithBlankDescription() {
        Course course = new Course();
        course.setTitle("Java Basics");
        course.setDescription("   ");

        assertThrows(ValidationException.class, () -> courseService.createCourse(course));
    }

    @Test
    @DisplayName("Should accept title with 99 characters")
    void testCreateCourseWithNearMaxLengthTitle() {
        Course course = new Course();
        course.setTitle("a".repeat(99));
        course.setDescription("A valid description");

        String result = courseService.createCourse(course);
        assertEquals("Course created successfully", result);
    }

    @Test
    @DisplayName("Should accept title with special characters")
    void testCreateCourseWithSpecialCharactersInTitle() {
        Course course = new Course();
        course.setTitle("Java & Web Development: Advanced Patterns");
        course.setDescription("Learn advanced patterns in Java");

        String result = courseService.createCourse(course);
        assertEquals("Course created successfully", result);
    }

    @Test
    @DisplayName("Should accept description with special characters")
    void testCreateCourseWithSpecialCharactersInDescription() {
        Course course = new Course();
        course.setTitle("Course Title");
        course.setDescription("Learn @Home! (100% Online & Interactive)");

        String result = courseService.createCourse(course);
        assertEquals("Course created successfully", result);
    }
}
