package com.encurso.domain.exceptions;

import com.encurso.domain.exception.CourseAlreadyExistsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("CourseAlreadyExistsException Tests")
class CourseAlreadyExistsExceptionTest {

    @Test
    @DisplayName("Should create CourseAlreadyExistsException with message")
    void testCourseAlreadyExistsExceptionCreation() {
        String message = "Java Basics course already exists";
        CourseAlreadyExistsException exception = new CourseAlreadyExistsException(message);
        
        assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName("Should throw CourseAlreadyExistsException")
    void testThrowCourseAlreadyExistsException() {
        assertThrows(CourseAlreadyExistsException.class, () -> {
            throw new CourseAlreadyExistsException("Course already exists");
        });
    }

    @Test
    @DisplayName("Should be instance of RuntimeException")
    void testCourseAlreadyExistsExceptionIsRuntimeException() {
        CourseAlreadyExistsException exception = new CourseAlreadyExistsException("Error");
        assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    @DisplayName("Should preserve custom message")
    void testExceptionMessagePreservation() {
        String message = "Course with title 'Python 101' already exists";
        CourseAlreadyExistsException exception = new CourseAlreadyExistsException(message);
        
        assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName("Should handle empty message")
    void testExceptionWithEmptyMessage() {
        CourseAlreadyExistsException exception = new CourseAlreadyExistsException("");
        assertEquals("", exception.getMessage());
    }
}
