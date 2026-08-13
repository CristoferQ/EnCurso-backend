package com.encurso.domain.repository;

import com.encurso.domain.entity.Enrollment;

import java.util.Optional;

public interface EnrollmentRepository {
    void save(Enrollment enrollment);
    Optional<Enrollment> findById(Long id);
}
