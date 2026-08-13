package com.encurso.domain.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Course Domain Tests")
class CourseTest {

    private Course course;

    @BeforeEach
    void setUp() {
        course = new Course();
    }

    @Test
    @DisplayName("Should create course with default constructor")
    void testCourseDefaultConstructor() {
        assertNotNull(course);
        assertNull(course.getId());
        assertNull(course.getTitle());
        assertNull(course.getDescription());
        assertNotNull(course.getEnrollments());
    }

    @Test
    @DisplayName("Should create course with parameterized constructor")
    void testCourseParameterizedConstructor() {
        Course newCourse = new Course("Java Basics", "Learn Java fundamentals");
        
        assertEquals("Java Basics", newCourse.getTitle());
        assertEquals("Learn Java fundamentals", newCourse.getDescription());
        assertNull(newCourse.getId());
        assertNotNull(newCourse.getEnrollments());
    }

    @Test
    @DisplayName("Should set and get id")
    void testSetAndGetId() {
        course.setId(1L);
        assertEquals(1L, course.getId());
    }

    @Test
    @DisplayName("Should set and get title")
    void testSetAndGetTitle() {
        course.setTitle("Advanced Java");
        assertEquals("Advanced Java", course.getTitle());
    }

    @Test
    @DisplayName("Should set and get description")
    void testSetAndGetDescription() {
        course.setDescription("Advanced Java programming course");
        assertEquals("Advanced Java programming course", course.getDescription());
    }

    @Test
    @DisplayName("Should initialize enrollments as empty list")
    void testEnrollmentsInitialized() {
        assertTrue(course.getEnrollments().isEmpty());
    }

    @Test
    @DisplayName("Should set and get enrollments list")
    void testSetAndGetEnrollments() {
        java.util.List<Enrollment> enrollments = new java.util.ArrayList<>();
        course.setEnrollments(enrollments);
        assertEquals(enrollments, course.getEnrollments());
    }

    @Test
    @DisplayName("Should have correct toString representation")
    void testToString() {
        course.setId(1L);
        course.setTitle("Java Basics");
        
        String result = course.toString();
        assertTrue(result.contains("Course{"));
        assertTrue(result.contains("id=1"));
        assertTrue(result.contains("title='Java Basics'"));
    }

    @Test
    @DisplayName("Should allow null values on setters")
    void testNullValuesAllowed() {
        course.setId(null);
        course.setTitle(null);
        course.setDescription(null);
        
        assertNull(course.getId());
        assertNull(course.getTitle());
        assertNull(course.getDescription());
    }

    @Test
    @DisplayName("Should accept maximum length title (100 chars)")
    void testMaxLengthTitle() {
        String maxTitle = "a".repeat(100);
        course.setTitle(maxTitle);
        assertEquals(maxTitle, course.getTitle());
    }

    @Test
    @DisplayName("Should accept empty string as title")
    void testEmptyTitleString() {
        course.setTitle("");
        assertEquals("", course.getTitle());
    }
}
