package com.encurso.persistence;

import com.encurso.domain.entity.Enrollment;
import com.encurso.domain.entity.User;
import com.encurso.domain.entity.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("In Memory Enrollment Repository Tests")
class InMemoryEnrollmentRepositoryTest {

    private InMemoryEnrollmentRepository repository;
    private Enrollment enrollment;
    private User user;
    private Course course;

    @BeforeEach
    void setUp() {
        repository = new InMemoryEnrollmentRepository();
        user = new User(1L, "John Doe", "john@email.com", "SecurePass1!", "student");
        course = new Course("Java Basics", "Learn Java from scratch");
        course.setId(1L);
        enrollment = new Enrollment(user, course);
        enrollment.setId(1L);
    }

    @Test
    @DisplayName("Should save enrollment successfully")
    void testSaveEnrollmentSuccessfully() {
        repository.save(enrollment);

        Optional<Enrollment> result = repository.findById(1L);
        assertTrue(result.isPresent());
        assertEquals("John Doe", result.get().getUser().getName());
        assertEquals("Java Basics", result.get().getCourse().getTitle());
    }

    @Test
    @DisplayName("Should not save enrollment with null id")
    void testSaveEnrollmentWithNullId() {
        enrollment.setId(null);
        repository.save(enrollment);

        Optional<Enrollment> result = repository.findById(1L);
        assertFalse(result.isPresent());
    }

    @Test
    @DisplayName("Should not save null enrollment")
    void testSaveNullEnrollment() {
        assertDoesNotThrow(() -> repository.save(null));

        Optional<Enrollment> result = repository.findById(1L);
        assertFalse(result.isPresent());
    }

    @Test
    @DisplayName("Should find enrollment by id")
    void testFindEnrollmentById() {
        repository.save(enrollment);

        Optional<Enrollment> result = repository.findById(1L);
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        assertEquals(user.getId(), result.get().getUser().getId());
        assertEquals(course.getId(), result.get().getCourse().getId());
    }

    @Test
    @DisplayName("Should return empty optional when enrollment not found")
    void testFindEnrollmentNotFound() {
        Optional<Enrollment> result = repository.findById(999L);
        assertFalse(result.isPresent());
    }

    @Test
    @DisplayName("Should update enrollment when saving with same id")
    void testUpdateEnrollmentWhenSavingWithSameId() {
        repository.save(enrollment);

        User newUser = new User(2L, "Jane Doe", "jane@email.com", "SecurePass1!", "instructor");
        Enrollment updatedEnrollment = new Enrollment(newUser, course);
        updatedEnrollment.setId(1L);
        repository.save(updatedEnrollment);

        Optional<Enrollment> result = repository.findById(1L);
        assertTrue(result.isPresent());
        assertEquals("Jane Doe", result.get().getUser().getName());
    }

    @Test
    @DisplayName("Should save multiple enrollments")
    void testSaveMultipleEnrollments() {
        User user2 = new User(2L, "Jane Doe", "jane@email.com", "SecurePass1!", "student");
        Enrollment enrollment2 = new Enrollment(user2, course);
        enrollment2.setId(2L);

        User user3 = new User(3L, "Bob Smith", "bob@email.com", "SecurePass1!", "student");
        Enrollment enrollment3 = new Enrollment(user3, course);
        enrollment3.setId(3L);

        repository.save(enrollment);
        repository.save(enrollment2);
        repository.save(enrollment3);

        assertTrue(repository.findById(1L).isPresent());
        assertTrue(repository.findById(2L).isPresent());
        assertTrue(repository.findById(3L).isPresent());
    }

    @Test
    @DisplayName("Should preserve enrollment data after save")
    void testPreserveEnrollmentDataAfterSave() {
        repository.save(enrollment);

        Optional<Enrollment> result = repository.findById(1L);
        assertTrue(result.isPresent());
        assertEquals(user.getId(), result.get().getUser().getId());
        assertEquals(course.getId(), result.get().getCourse().getId());
    }
}
