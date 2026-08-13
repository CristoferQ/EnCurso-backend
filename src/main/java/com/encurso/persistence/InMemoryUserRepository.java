package com.encurso.persistence;

import com.encurso.domain.entity.User;
import com.encurso.domain.repository.UserRepository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryUserRepository implements UserRepository {

    private final Map<Long, User> storage = new ConcurrentHashMap<>();

    @Override
    public void save(User user) {
        if (user != null && user.getId() != null) {
            storage.put(user.getId(), user);
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }
}
