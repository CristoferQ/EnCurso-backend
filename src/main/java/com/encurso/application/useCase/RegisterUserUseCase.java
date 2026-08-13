package com.encurso.application.useCase;

import com.encurso.domain.entity.User;
import com.encurso.domain.repository.UserRepository;

public class RegisterUserUseCase {
    private final UserRepository userRepository;

    public RegisterUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void execute(User user){
        if(userRepository.findById(user.getId()).isPresent()){
            throw new IllegalArgumentException("El ID del usuario ya existe.");
        }

        userRepository.save(user);
    }
}
