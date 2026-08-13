package com.encurso.application.service;

import com.encurso.domain.exception.ValidationException;
import com.encurso.application.service.EnrollmentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("EnrollmentService Tests")
class EnrollmentServiceTest {

    private final EnrollmentService enrollmentService = new EnrollmentService();

    @Test
    @DisplayName("Should validate null user id")
    void testEnrollWithNullUserId() {
        assertThrows(ValidationException.class, () -> enrollmentService.enroll(null, 1L));
    }

    @Test
    @DisplayName("Should validate null course id")
    void testEnrollWithNullCourseId() {
        assertThrows(ValidationException.class, () -> enrollmentService.enroll(1L, null));
    }

    @Test
    @DisplayName("Should validate both user id and course id are null")
    void testEnrollWithBothIdsNull() {
        assertThrows(ValidationException.class, () -> enrollmentService.enroll(null, null));
    }

    @Test
    @DisplayName("Should accept valid enrollment data")
    void testEnrollWithValidData() {
        String result = enrollmentService.enroll(1L, 1L);
        assertEquals("Enrollment created successfully", result);
    }

    @Test
    @DisplayName("Should accept enrollment with different valid ids")
    void testEnrollWithDifferentIds() {
        String result = enrollmentService.enroll(2L, 3L);
        assertEquals("Enrollment created successfully", result);
    }

    @Test
    @DisplayName("Should accept enrollment with large positive ids")
    void testEnrollWithLargeIds() {
        String result = enrollmentService.enroll(999999999L, 888888888L);
        assertEquals("Enrollment created successfully", result);
    }

    @Test
    @DisplayName("Should accept enrollment with id 0")
    void testEnrollWithZeroIds() {
        String result = enrollmentService.enroll(0L, 0L);
        assertEquals("Enrollment created successfully", result);
    }

    @Test
    @DisplayName("Should validate null user id throws correct exception type")
    void testEnrollWithNullUserIdExceptionMessage() {
        try {
            enrollmentService.enroll(null, 1L);
            fail("Should have thrown ValidationException");
        } catch (ValidationException e) {
            assertTrue(e.getMessage().contains("User id"));
        }
    }

    @Test
    @DisplayName("Should validate null course id throws correct exception type")
    void testEnrollWithNullCourseIdExceptionMessage() {
        try {
            enrollmentService.enroll(1L, null);
            fail("Should have thrown ValidationException");
        } catch (ValidationException e) {
            assertTrue(e.getMessage().contains("Course id"));
        }
    }

    @Test
    @DisplayName("Should accept same user and course combination")
    void testEnrollWithSameUserAndCourse() {
        String result = enrollmentService.enroll(1L, 1L);
        assertEquals("Enrollment created successfully", result);
    }
}
