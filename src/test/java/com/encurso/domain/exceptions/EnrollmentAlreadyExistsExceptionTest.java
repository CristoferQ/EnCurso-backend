package com.encurso.domain.exceptions;

import com.encurso.domain.exception.EnrollmentAlreadyExistsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("EnrollmentAlreadyExistsException Tests")
class EnrollmentAlreadyExistsExceptionTest {

    @Test
    @DisplayName("Should create EnrollmentAlreadyExistsException with message")
    void testEnrollmentAlreadyExistsExceptionCreation() {
        String message = "User is already enrolled in this course.";
        EnrollmentAlreadyExistsException exception = new EnrollmentAlreadyExistsException(message);

        assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName("Should throw EnrollmentAlreadyExistsException")
    void testThrowEnrollmentAlreadyExistsException() {
        assertThrows(EnrollmentAlreadyExistsException.class, () -> {
            throw new EnrollmentAlreadyExistsException("Already enrolled");
        });
    }

    @Test
    @DisplayName("Should be instance of RuntimeException")
    void testEnrollmentAlreadyExistsExceptionIsRuntimeException() {
        EnrollmentAlreadyExistsException exception = new EnrollmentAlreadyExistsException("Error");
        assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    @DisplayName("Should preserve custom message")
    void testExceptionMessagePreservation() {
        String message = "User with id 1 is already enrolled in course with id 5";
        EnrollmentAlreadyExistsException exception = new EnrollmentAlreadyExistsException(message);

        assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName("Should handle empty message")
    void testExceptionWithEmptyMessage() {
        EnrollmentAlreadyExistsException exception = new EnrollmentAlreadyExistsException("");
        assertEquals("", exception.getMessage());
    }
}
