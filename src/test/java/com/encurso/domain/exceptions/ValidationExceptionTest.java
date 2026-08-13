package com.encurso.domain.exceptions;

import com.encurso.domain.exception.ValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ValidationException Tests")
class ValidationExceptionTest {

    @Test
    @DisplayName("Should create ValidationException with message")
    void testValidationExceptionCreation() {
        String message = "Email must not be empty.";
        ValidationException exception = new ValidationException(message);
        
        assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName("Should throw ValidationException")
    void testThrowValidationException() {
        assertThrows(ValidationException.class, () -> {
            throw new ValidationException("Validation error");
        });
    }

    @Test
    @DisplayName("Should catch ValidationException as RuntimeException")
    void testValidationExceptionIsRuntimeException() {
        ValidationException exception = new ValidationException("Error");
        assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    @DisplayName("Should preserve message through exception chain")
    void testExceptionMessage() {
        String message = "Password cannot be null.";
        try {
            throw new ValidationException(message);
        } catch (ValidationException e) {
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    @DisplayName("Should handle empty message")
    void testExceptionWithEmptyMessage() {
        ValidationException exception = new ValidationException("");
        assertEquals("", exception.getMessage());
    }

    @Test
    @DisplayName("Should handle null message")
    void testExceptionWithNullMessage() {
        ValidationException exception = new ValidationException(null);
        assertNull(exception.getMessage());
    }

    @Test
    @DisplayName("Should handle multi-line error messages")
    void testExceptionWithMultiLineMessage() {
        String message = "Field validation error:\n - Email is invalid\n - Password too short";
        ValidationException exception = new ValidationException(message);
        
        assertEquals(message, exception.getMessage());
        assertTrue(exception.getMessage().contains("Email is invalid"));
    }
}
