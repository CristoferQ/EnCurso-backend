package com.encurso.application.useCase;

import com.encurso.domain.entity.Enrollment;
import com.encurso.domain.entity.User;
import com.encurso.domain.entity.Course;
import com.encurso.domain.exception.UserNotFoundException;
import com.encurso.domain.repository.EnrollmentRepository;
import com.encurso.domain.repository.UserRepository;
import com.encurso.domain.repository.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Create Enrollment Use Case Tests")
class CreateEnrollmentUseCaseTest {

    private CreateEnrollmentUseCase createEnrollmentUseCase;
    private EnrollmentRepository enrollmentRepository;
    private UserRepository userRepository;
    private CourseRepository courseRepository;
    private User user;
    private Course course;
    private Enrollment enrollment;

    @BeforeEach
    void setUp() {
        enrollmentRepository = mock(EnrollmentRepository.class);
        userRepository = mock(UserRepository.class);
        courseRepository = mock(CourseRepository.class);
        createEnrollmentUseCase = new CreateEnrollmentUseCase(enrollmentRepository, userRepository, courseRepository);

        user = new User(1L, "John Doe", "john@email.com", "SecurePass123!", "student");
        course = new Course("Java Basics", "Learn Java from scratch");
        course.setId(1L);
        enrollment = new Enrollment(user, course);
    }

    @Test
    @DisplayName("Should create enrollment when user and course exist")
    void testCreateEnrollmentSuccessfully() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(courseRepository.findById(course.getId())).thenReturn(Optional.of(course));

        assertDoesNotThrow(() -> createEnrollmentUseCase.execute(enrollment));

        verify(enrollmentRepository, times(1)).save(enrollment);
    }

    @Test
    @DisplayName("Should throw UserNotFoundException when user does not exist")
    void testCreateEnrollmentWithNonExistentUser() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> createEnrollmentUseCase.execute(enrollment));
        verify(enrollmentRepository, never()).save(enrollment);
    }

    @Test
    @DisplayName("Should throw UserNotFoundException when course does not exist")
    void testCreateEnrollmentWithNonExistentCourse() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(courseRepository.findById(course.getId())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> createEnrollmentUseCase.execute(enrollment));
        verify(enrollmentRepository, never()).save(enrollment);
    }

    @Test
    @DisplayName("Should throw exception when user is null")
    void testCreateEnrollmentWithNullUser() {
        enrollment.setUser(null);

        assertThrows(NullPointerException.class, () -> createEnrollmentUseCase.execute(enrollment));
    }

    @Test
    @DisplayName("Should throw exception when course is null")
    void testCreateEnrollmentWithNullCourse() {
        enrollment.setCourse(null);

        assertThrows(UserNotFoundException.class, () -> createEnrollmentUseCase.execute(enrollment));
    }
}
