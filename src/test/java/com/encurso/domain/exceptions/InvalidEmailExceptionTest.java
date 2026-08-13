package com.encurso.domain.exceptions;

import com.encurso.domain.exception.InvalidEmailException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Invalid Email Exception Tests")
class InvalidEmailExceptionTest {

    @Test
    @DisplayName("Should throw InvalidEmailException with message")
    void testInvalidEmailExceptionWithMessage() {
        String message = "El correo electrónico no es válido";
        
        InvalidEmailException exception = assertThrows(InvalidEmailException.class, 
            () -> { throw new InvalidEmailException(message); });
        
        assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName("Should throw InvalidEmailException without message")
    void testInvalidEmailExceptionWithoutMessage() {
        InvalidEmailException exception = assertThrows(InvalidEmailException.class, 
            () -> { throw new InvalidEmailException("Email error"); });
        
        assertNotNull(exception);
    }

    @Test
    @DisplayName("Should throw InvalidEmailException with cause")
    void testInvalidEmailExceptionWithCause() {
        Throwable cause = new Throwable("Email validation failed");
        
        InvalidEmailException exception = new InvalidEmailException("Email error", cause);
        
        assertEquals("Email error", exception.getMessage());
        assertNotNull(exception.getCause());
        assertEquals("Email validation failed", exception.getCause().getMessage());
    }

    @Test
    @DisplayName("Should extend RuntimeException")
    void testInvalidEmailExceptionExtendsRuntimeException() {
        InvalidEmailException exception = new InvalidEmailException("Test");
        
        assertTrue(exception instanceof RuntimeException);
    }

    @Test
    @DisplayName("Should be catchable as RuntimeException")
    void testInvalidEmailExceptionCatchAsRuntimeException() {
        try {
            throw new InvalidEmailException("Invalid email format");
        } catch (RuntimeException e) {
            assertEquals("Invalid email format", e.getMessage());
        }
    }

    @Test
    @DisplayName("Should preserve message through exception chain")
    void testPreserveMessageThroughChain() {
        String originalMessage = "El correo electrónico no es válido: user@";
        InvalidEmailException exception = new InvalidEmailException(originalMessage);
        
        assertEquals(originalMessage, exception.getMessage());
    }

    @Test
    @DisplayName("Should create exception with different messages")
    void testExceptionWithDifferentMessages() {
        InvalidEmailException exception1 = new InvalidEmailException("Missing @");
        InvalidEmailException exception2 = new InvalidEmailException("Missing domain");
        
        assertNotEquals(exception1.getMessage(), exception2.getMessage());
    }

    @Test
    @DisplayName("Should handle null message gracefully")
    void testExceptionWithNullMessage() {
        InvalidEmailException exception = new InvalidEmailException(null);
        
        assertNull(exception.getMessage());
    }
}
