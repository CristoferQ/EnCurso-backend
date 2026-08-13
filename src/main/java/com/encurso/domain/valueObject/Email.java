package com.encurso.domain.valueObject;

import com.encurso.domain.exception.InvalidEmailException;

public record Email(String value) {
  public Email {
    String emailRegex = "^[a-zA-Z0-9]+([._%+-][a-zA-Z0-9]+)*@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    String cleanValue = value.trim().toLowerCase();
    if (!cleanValue.matches(emailRegex)) {
      throw new InvalidEmailException("El correo electrónico no es válido: " + value);
    }
    value = value.trim().toLowerCase();
  }
}
