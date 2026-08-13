package com.encurso.domain.repository;

import com.encurso.domain.entity.User;

import java.util.Optional;

public interface UserRepository {
    void save(User user);
    Optional<User> findById(Long id);
}
