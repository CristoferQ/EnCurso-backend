package com.encurso.persistence;

import com.encurso.domain.entity.Enrollment;
import com.encurso.domain.repository.EnrollmentRepository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryEnrollmentRepository implements EnrollmentRepository {

    private final Map<Long, Enrollment> storage = new ConcurrentHashMap<>();

    @Override
    public void save(Enrollment enrollment) {
        if (enrollment != null && enrollment.getId() != null) {
            storage.put(enrollment.getId(), enrollment);
        }
    }

    @Override
    public Optional<Enrollment> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }
}
