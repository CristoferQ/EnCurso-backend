package com.encurso.domain.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Enrollment Domain Tests")
class EnrollmentTest {

    private Enrollment enrollment;
    private User user;
    private Course course;

    @BeforeEach
    void setUp() {
        enrollment = new Enrollment();
        user = new User(1L,"John Doe", "john@email.com", "SecurePass123!", "student");
        course = new Course("Java Basics", "Learn Java");
    }

    @Test
    @DisplayName("Should create enrollment with default constructor")
    void testEnrollmentDefaultConstructor() {
        assertNotNull(enrollment);
        assertNull(enrollment.getId());
        assertNull(enrollment.getUser());
        assertNull(enrollment.getCourse());
    }

    @Test
    @DisplayName("Should create enrollment with parameterized constructor")
    void testEnrollmentParameterizedConstructor() {
        Enrollment newEnrollment = new Enrollment(user, course);
        
        assertEquals(user, newEnrollment.getUser());
        assertEquals(course, newEnrollment.getCourse());
        assertNull(newEnrollment.getId());
    }

    @Test
    @DisplayName("Should set and get id")
    void testSetAndGetId() {
        enrollment.setId(1L);
        assertEquals(1L, enrollment.getId());
    }

    @Test
    @DisplayName("Should set and get user")
    void testSetAndGetUser() {
        enrollment.setUser(user);
        assertEquals(user, enrollment.getUser());
    }

    @Test
    @DisplayName("Should set and get course")
    void testSetAndGetCourse() {
        enrollment.setCourse(course);
        assertEquals(course, enrollment.getCourse());
    }

    @Test
    @DisplayName("Should have correct toString representation")
    void testToString() {
        user.setId(1L);
        course.setId(2L);
        enrollment.setId(1L);
        enrollment.setUser(user);
        enrollment.setCourse(course);
        
        String result = enrollment.toString();
        assertTrue(result.contains("Enrollment{"));
        assertTrue(result.contains("id=1"));
        assertTrue(result.contains("user=John Doe"));
        assertTrue(result.contains("course=Java Basics"));
    }

    @Test
    @DisplayName("Should allow null user")
    void testNullUser() {
        enrollment.setUser(null);
        assertNull(enrollment.getUser());
    }

    @Test
    @DisplayName("Should allow null course")
    void testNullCourse() {
        enrollment.setCourse(null);
        assertNull(enrollment.getCourse());
    }

    @Test
    @DisplayName("Should allow null id")
    void testNullId() {
        enrollment.setId(null);
        assertNull(enrollment.getId());
    }

    @Test
    @DisplayName("Should handle multiple users in different enrollments")
    void testMultipleEnrollmentsWithDifferentUsers() {
        User user2 = new User(1L, "Jane Doe", "jane@email.com", "SecurePass456!", "student");
        
        Enrollment enrollment1 = new Enrollment(user, course);
        Enrollment enrollment2 = new Enrollment(user2, course);
        
        assertEquals(user, enrollment1.getUser());
        assertEquals(user2, enrollment2.getUser());
        assertEquals(course, enrollment1.getCourse());
        assertEquals(course, enrollment2.getCourse());
    }

    @Test
    @DisplayName("Should handle multiple courses for same user")
    void testMultipleEnrollmentsWithDifferentCourses() {
        Course course2 = new Course("Advanced Java", "Advanced concepts");
        
        Enrollment enrollment1 = new Enrollment(user, course);
        Enrollment enrollment2 = new Enrollment(user, course2);
        
        assertEquals(user, enrollment1.getUser());
        assertEquals(user, enrollment2.getUser());
        assertEquals(course, enrollment1.getCourse());
        assertEquals(course2, enrollment2.getCourse());
    }
}
