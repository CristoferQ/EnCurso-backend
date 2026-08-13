package com.encurso.domain.valueObject;

import com.encurso.domain.exception.ValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Password Value Object Tests")
class PasswordTest {

    @Test
    @DisplayName("Should create password with valid requirements")
    void testValidPassword() {
        Password password = new Password("ValidPassword123!");
        assertNotNull(password);
        assertEquals("ValidPassword123!", password.value());
    }

    @Test
    @DisplayName("Should throw exception for empty password")
    void testEmptyPassword() {
        assertThrows(ValidationException.class, () -> new Password(""));
    }

    @Test
    @DisplayName("Should throw exception for null password")
    void testNullPassword() {
        assertThrows(ValidationException.class, () -> new Password(null));
    }

    @Test
    @DisplayName("Should throw exception for password with less than 8 characters")
    void testPasswordTooShort() {
        assertThrows(ValidationException.class, () -> new Password("Pass1!"));
    }

    @Test
    @DisplayName("Should throw exception for password without uppercase letter")
    void testPasswordWithoutUppercase() {
        assertThrows(ValidationException.class, () -> new Password("password123!"));
    }

    @Test
    @DisplayName("Should throw exception for password without lowercase letter")
    void testPasswordWithoutLowercase() {
        assertThrows(ValidationException.class, () -> new Password("PASSWORD123!"));
    }

    @Test
    @DisplayName("Should throw exception for password without number")
    void testPasswordWithoutNumber() {
        assertThrows(ValidationException.class, () -> new Password("PasswordTest!"));
    }

    @Test
    @DisplayName("Should throw exception for password without special character")
    void testPasswordWithoutSpecialChar() {
        assertThrows(ValidationException.class, () -> new Password("Password123"));
    }

    @Test
    @DisplayName("Should accept password with various special characters")
    void testPasswordWithDifferentSpecialCharacters() {
        assertDoesNotThrow(() -> new Password("ValidPass1@"));
        assertDoesNotThrow(() -> new Password("ValidPass1#"));
        assertDoesNotThrow(() -> new Password("ValidPass1$"));
        assertDoesNotThrow(() -> new Password("ValidPass1%"));
    }

    @Test
    @DisplayName("Should throw exception for password with only spaces")
    void testPasswordOnlySpaces() {
        assertThrows(ValidationException.class, () -> new Password("        "));
    }
}
