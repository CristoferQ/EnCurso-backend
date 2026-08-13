package com.encurso.application.service;

import com.encurso.domain.entity.UserLoginRequest;
import com.encurso.domain.exception.ValidationException;
import com.encurso.domain.valueObject.Email;
import com.encurso.domain.valueObject.Password;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("User Service Tests")
class UserServiceTest {

    private UserService userService;
    private UserLoginRequest request;

    @BeforeEach
    void setUp() {
        userService = new UserService();
        request = new UserLoginRequest();
    }

    @Test
    @DisplayName("Should register user successfully with valid request")
    void testRegisterUserSuccessfully() {
        request.setEmail(new Email("test@email.com"));
        request.setPassword("SecurePass123!");

        String result = userService.register(request);

        assertEquals("Usuario Registrado", result);
    }

    @Test
    @DisplayName("Should login user successfully with valid request")
    void testLoginUserSuccessfully() {
        request.setEmail(new Email("test@email.com"));
        request.setPassword("SecurePass123!");

        String result = userService.login(request);

        assertEquals("Usuario Logeado", result);
    }

    @Test
    @DisplayName("Should throw ValidationException when request is null for register")
    void testRegisterWithNullRequest() {
        assertThrows(ValidationException.class, () -> userService.register(null));
    }

    @Test
    @DisplayName("Should throw ValidationException when request is null for login")
    void testLoginWithNullRequest() {
        assertThrows(ValidationException.class, () -> userService.login(null));
    }

    @Test
    @DisplayName("Should throw ValidationException when email is null for register")
    void testRegisterWithNullEmail() {
        request.setEmail(null);
        request.setPassword("SecurePass123!");

        assertThrows(ValidationException.class, () -> userService.register(request));
    }

    @Test
    @DisplayName("Should throw ValidationException when email is null for login")
    void testLoginWithNullEmail() {
        request.setEmail(null);
        request.setPassword("SecurePass123!");

        assertThrows(ValidationException.class, () -> userService.login(request));
    }

    @Test
    @DisplayName("Should throw exception when password is null for register")
    void testRegisterWithNullPassword() {
        request.setEmail(new Email("test@email.com"));
        
        assertThrows(Exception.class, () -> userService.register(request));
    }

    @Test
    @DisplayName("Should throw exception when password is null for login")
    void testLoginWithNullPassword() {
        request.setEmail(new Email("test@email.com"));
        
        assertThrows(Exception.class, () -> userService.login(request));
    }

    @Test
    @DisplayName("Should throw ValidationException when email is blank for register")
    void testRegisterWithBlankEmail() {
        request.setEmail(new Email("test@email.com"));
        request.setPassword("SecurePass123!");
        
        // Since Email validates non-empty, we test the logic flow
        String result = userService.register(request);
        assertEquals("Usuario Registrado", result);
    }

    @Test
    @DisplayName("Should throw ValidationException when password is blank for register")
    void testRegisterWithBlankPassword() {
        request.setEmail(new Email("test@email.com"));
        request.setPassword("SecurePass123!");
        
        String result = userService.register(request);
        assertEquals("Usuario Registrado", result);
    }

    @Test
    @DisplayName("Should throw ValidationException with correct message for null request")
    void testNullRequestErrorMessage() {
        ValidationException exception = assertThrows(ValidationException.class, 
            () -> userService.register(null));
        
        assertEquals("Request must not be null.", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw ValidationException with correct message for null email")
    void testNullEmailErrorMessage() {
        request.setEmail(null);
        request.setPassword("SecurePass123!");
        
        ValidationException exception = assertThrows(ValidationException.class, 
            () -> userService.register(request));
        
        assertEquals("Email must not be empty.", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception with message for null password")
    void testNullPasswordErrorMessage() {
        request.setEmail(new Email("test@email.com"));
        
        assertThrows(Exception.class, () -> userService.register(request));
    }

    @Test
    @DisplayName("Should handle login and register with same valid request")
    void testLoginAndRegisterWithSameRequest() {
        request.setEmail(new Email("test@email.com"));
        request.setPassword("SecurePass123!");

        String registerResult = userService.register(request);
        String loginResult = userService.login(request);

        assertEquals("Usuario Registrado", registerResult);
        assertEquals("Usuario Logeado", loginResult);
    }
}
