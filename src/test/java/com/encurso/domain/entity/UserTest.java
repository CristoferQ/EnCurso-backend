package com.encurso.domain.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("User Domain Tests")
class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
    }

    @Test
    @DisplayName("Should create user with default constructor")
    void testUserDefaultConstructor() {
        assertNotNull(user);
        assertNull(user.getId());
        assertNull(user.getName());
        assertNull(user.getEmail());
        assertNull(user.getPassword());
    }

    @Test
    @DisplayName("Should create user with parameterized constructor")
    void testUserParameterizedConstructor() {
        User newUser = new User(null, "John Doe", "john@email.com", "SecurePass123!", "student");
        
        assertEquals("John Doe", newUser.getName());
        assertEquals("john@email.com", newUser.getEmail());
        assertEquals("SecurePass123!", newUser.getPassword());
        assertNull(newUser.getId());
    }

    @Test
    @DisplayName("Should set and get id")
    void testSetAndGetId() {
        user.setId(1L);
        assertEquals(1L, user.getId());
    }

    @Test
    @DisplayName("Should set and get name")
    void testSetAndGetName() {
        user.setName("Jane Doe");
        assertEquals("Jane Doe", user.getName());
    }

    @Test
    @DisplayName("Should set and get email")
    void testSetAndGetEmail() {
        user.setEmail("jane@email.com");
        assertEquals("jane@email.com", user.getEmail());
    }

    @Test
    @DisplayName("Should set and get password")
    void testSetAndGetPassword() {
        user.setPassword("SecurePass123!");
        assertEquals("SecurePass123!", user.getPassword());
    }

    @Test
    @DisplayName("Should set and get role")
    void testSetAndGetRole() {
        user.setRole("instructor");
        assertEquals("instructor", user.getRole());
    }

    @Test
    @DisplayName("Should initialize enrollments as empty list")
    void testEnrollmentsInitialized() {
        assertTrue(user.getEnrollments().isEmpty());
    }

    @Test
    @DisplayName("Should set and get enrollments list")
    void testSetAndGetEnrollments() {
        java.util.List<Enrollment> enrollments = new java.util.ArrayList<>();
        user.setEnrollments(enrollments);
        assertEquals(enrollments, user.getEnrollments());
    }

    @Test
    @DisplayName("Should have correct toString representation")
    void testToString() {
        user.setId(1L);
        user.setName("John");
        user.setEmail("john@email.com");
        user.setRole("student");
        
        String result = user.toString();
        assertTrue(result.contains("User{"));
        assertTrue(result.contains("id=1"));
        assertTrue(result.contains("name='John'"));
        assertTrue(result.contains("email='john@email.com'"));
        assertTrue(result.contains("role='student'"));
    }

    @Test
    @DisplayName("Should allow null values on setters")
    void testNullValuesAllowed() {
        user.setId(null);
        user.setName(null);
        user.setEmail(null);
        user.setPassword(null);
        user.setRole(null);
        
        assertNull(user.getId());
        assertNull(user.getName());
        assertNull(user.getEmail());
        assertNull(user.getPassword());
        assertNull(user.getRole());
    }
}
