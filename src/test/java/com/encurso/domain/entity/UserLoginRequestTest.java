package com.encurso.domain.entity;

import com.encurso.domain.valueObject.Email;
import com.encurso.domain.valueObject.Password;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("User Login Request Tests")
class UserLoginRequestTest {

    private UserLoginRequest request;
    private Email email;
    private Password password;

    @BeforeEach
    void setUp() {
        request = new UserLoginRequest();
        email = new Email("test@email.com");
        password = new Password("SecurePass123!");
    }

    @Test
    @DisplayName("Should create empty login request")
    void testCreateEmptyLoginRequest() {
        UserLoginRequest emptyRequest = new UserLoginRequest();
        assertNotNull(emptyRequest);
    }

    @Test
    @DisplayName("Should set and get email")
    void testSetAndGetEmail() {
        request.setEmail(email);
        assertEquals(email, request.getEmail());
    }

    @Test
    @DisplayName("Should set and get password")
    void testSetAndGetPassword() {
        request.setPassword("SecurePass123!");
        assertEquals("SecurePass123!", request.getPassword());
    }

    @Test
    @DisplayName("Should get password value correctly")
    void testGetPasswordValue() {
        String passwordString = "SecurePass123!";
        request.setPassword(passwordString);
        
        assertEquals(passwordString, request.getPassword());
    }

    @Test
    @DisplayName("Should set email multiple times")
    void testSetEmailMultipleTimes() {
        Email email1 = new Email("first@email.com");
        Email email2 = new Email("second@email.com");
        
        request.setEmail(email1);
        assertEquals(email1, request.getEmail());
        
        request.setEmail(email2);
        assertEquals(email2, request.getEmail());
    }

    @Test
    @DisplayName("Should set password multiple times")
    void testSetPasswordMultipleTimes() {
        request.setPassword("FirstPassword123!");
        assertEquals("FirstPassword123!", request.getPassword());
        
        request.setPassword("SecondPassword123!");
        assertEquals("SecondPassword123!", request.getPassword());
    }

    @Test
    @DisplayName("Should handle email null value")
    void testSetNullEmail() {
        request.setEmail(null);
        assertNull(request.getEmail());
    }

    @Test
    @DisplayName("Should throw exception when setting invalid password")
    void testSetInvalidPassword() {
        assertThrows(Exception.class, () -> request.setPassword("weak"));
    }

    @Test
    @DisplayName("Should set valid email")
    void testSetValidEmail() {
        Email validEmail = new Email("valid@email.com");
        request.setEmail(validEmail);
        assertEquals(validEmail, request.getEmail());
    }

    @Test
    @DisplayName("Should set valid password")
    void testSetValidPassword() {
        String validPassword = "ValidPass123!";
        request.setPassword(validPassword);
        assertEquals(validPassword, request.getPassword());
    }

    @Test
    @DisplayName("Should handle different valid emails")
    void testDifferentValidEmails() {
        Email email1 = new Email("user1@email.com");
        Email email2 = new Email("user2@email.com");
        Email email3 = new Email("user3@email.com");
        
        request.setEmail(email1);
        assertEquals(email1, request.getEmail());
        
        request.setEmail(email2);
        assertEquals(email2, request.getEmail());
        
        request.setEmail(email3);
        assertEquals(email3, request.getEmail());
    }

    @Test
    @DisplayName("Should maintain independence of email and password")
    void testIndependenceOfEmailAndPassword() {
        request.setEmail(email);
        request.setPassword("FirstPassword123!");
        
        assertEquals(email, request.getEmail());
        assertEquals("FirstPassword123!", request.getPassword());
        
        Email newEmail = new Email("newemail@email.com");
        request.setEmail(newEmail);
        
        assertEquals(newEmail, request.getEmail());
        assertEquals("FirstPassword123!", request.getPassword());
    }
}
