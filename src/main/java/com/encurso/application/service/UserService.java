package com.encurso.application.service;

import com.encurso.domain.entity.UserLoginRequest;
import com.encurso.domain.exception.ValidationException;

public class UserService {

    public String register(UserLoginRequest request) {
        validateLoginRequest(request);
        return "Usuario Registrado";
    }

    public String login(UserLoginRequest request) {
        validateLoginRequest(request);
        return "Usuario Logeado";
    }

    private void validateLoginRequest(UserLoginRequest request) {
        if (request == null) {
            throw new ValidationException("Request must not be null.");
        }
        if (request.getEmail() == null || request.getEmail().toString().isBlank()) {
            throw new ValidationException("Email must not be empty.");
        }
        if (request.getPassword() == null || request.getPassword().isBlank()) {
            throw new ValidationException("Password must not be empty.");
        }
    }
}