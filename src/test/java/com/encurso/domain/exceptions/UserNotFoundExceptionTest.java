package com.encurso.domain.exceptions;

import com.encurso.domain.exception.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("UserNotFoundException Tests")
class UserNotFoundExceptionTest {

    @Test
    @DisplayName("Should create UserNotFoundException with email")
    void testUserNotFoundExceptionCreation() {
        String email = "nonexistent@email.com";
        UserNotFoundException exception = new UserNotFoundException(email);
        
        assertTrue(exception.getMessage().contains(email));
        assertTrue(exception.getMessage().contains("not found"));
    }

    @Test
    @DisplayName("Should throw UserNotFoundException")
    void testThrowUserNotFoundException() {
        assertThrows(UserNotFoundException.class, () -> {
            throw new UserNotFoundException("test@email.com");
        });
    }

    @Test
    @DisplayName("Should be instance of RuntimeException")
    void testUserNotFoundExceptionIsRuntimeException() {
        UserNotFoundException exception = new UserNotFoundException("test@email.com");
        assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    @DisplayName("Should format message with email correctly")
    void testExceptionMessageFormat() {
        String email = "john@example.com";
        UserNotFoundException exception = new UserNotFoundException(email);
        String message = exception.getMessage();
        
        assertTrue(message.contains("User with email"));
        assertTrue(message.contains(email));
        assertTrue(message.contains("was not found"));
    }

    @Test
    @DisplayName("Should handle special characters in email")
    void testExceptionWithSpecialCharactersEmail() {
        String email = "user+tag@sub.example.com";
        UserNotFoundException exception = new UserNotFoundException(email);
        
        assertTrue(exception.getMessage().contains(email));
    }
}
