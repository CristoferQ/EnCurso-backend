package com.encurso.domain.exception;

public class EnrollmentAlreadyExistsException extends RuntimeException {
  public EnrollmentAlreadyExistsException(String message) {
    super(message);
  }
}
