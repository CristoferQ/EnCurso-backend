package com.encurso.domain.valueObject;

import com.encurso.domain.exception.ValidationException;

public record Password(String value) {
    public Password {
        if (value == null) {
            throw new ValidationException("La contraseña no puede ser nula.");
        }
        
        if (value.trim().isEmpty()) {
            throw new ValidationException("La contraseña no puede estar vacía.");
        }
        
        if (value.length() < 8) {
            throw new ValidationException("La contraseña debe tener al menos 8 caracteres.");
        }
        
        if (!value.matches(".*[A-Z].*")) {
            throw new ValidationException("La contraseña debe contener al menos una letra mayúscula.");
        }
        
        if (!value.matches(".*[a-z].*")) {
            throw new ValidationException("La contraseña debe contener al menos una letra minúscula.");
        }
        
        if (!value.matches(".*[0-9].*")) {
            throw new ValidationException("La contraseña debe contener al menos un número.");
        }
        
        if (!value.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\",./<>?].*")) {
            throw new ValidationException("La contraseña debe contener al menos un carácter especial.");
        }
    }
}
