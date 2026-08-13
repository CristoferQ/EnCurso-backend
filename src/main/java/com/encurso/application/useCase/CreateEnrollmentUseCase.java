package com.encurso.application.useCase;

import com.encurso.domain.entity.Enrollment;
import com.encurso.domain.exception.UserNotFoundException;
import com.encurso.domain.repository.EnrollmentRepository;
import com.encurso.domain.repository.UserRepository;
import com.encurso.domain.repository.CourseRepository;

public class CreateEnrollmentUseCase {
    private final EnrollmentRepository enrollmentRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    public CreateEnrollmentUseCase(EnrollmentRepository enrollmentRepository, 
                                   UserRepository userRepository,
                                   CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    public void execute(Enrollment enrollment) {
        if (userRepository.findById(enrollment.getUser().getId()).isEmpty()) {
            throw new UserNotFoundException("El usuario con ID " + enrollment.getUser().getId() + " no existe.");
        }

        if (courseRepository.findById(enrollment.getCourse().getId()).isEmpty()) {
            throw new UserNotFoundException("El curso con ID " + enrollment.getCourse().getId() + " no existe.");
        }

        enrollmentRepository.save(enrollment);
    }
}
