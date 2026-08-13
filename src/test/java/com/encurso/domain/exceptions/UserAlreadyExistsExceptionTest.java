package com.encurso.domain.exceptions;

import com.encurso.domain.exception.UserAlreadyExistsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("UserAlreadyExistsException Tests")
class UserAlreadyExistsExceptionTest {

    @Test
    @DisplayName("Should create UserAlreadyExistsException with email")
    void testUserAlreadyExistsExceptionCreation() {
        String email = "existing@email.com";
        UserAlreadyExistsException exception = new UserAlreadyExistsException(email);
        
        assertTrue(exception.getMessage().contains(email));
        assertTrue(exception.getMessage().contains("already exists"));
    }

    @Test
    @DisplayName("Should throw UserAlreadyExistsException")
    void testThrowUserAlreadyExistsException() {
        assertThrows(UserAlreadyExistsException.class, () -> {
            throw new UserAlreadyExistsException("duplicate@email.com");
        });
    }

    @Test
    @DisplayName("Should be instance of RuntimeException")
    void testUserAlreadyExistsExceptionIsRuntimeException() {
        UserAlreadyExistsException exception = new UserAlreadyExistsException("test@email.com");
        assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    @DisplayName("Should format message with email correctly")
    void testExceptionMessageFormat() {
        String email = "jane@example.com";
        UserAlreadyExistsException exception = new UserAlreadyExistsException(email);
        String message = exception.getMessage();
        
        assertTrue(message.contains("User with email"));
        assertTrue(message.contains(email));
        assertTrue(message.contains("already exists"));
    }

    @Test
    @DisplayName("Should handle special characters in email")
    void testExceptionWithSpecialCharactersEmail() {
        String email = "user.name+tag@example.co.uk";
        UserAlreadyExistsException exception = new UserAlreadyExistsException(email);
        
        assertTrue(exception.getMessage().contains(email));
    }
}
