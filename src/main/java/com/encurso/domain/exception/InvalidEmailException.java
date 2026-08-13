package com.encurso.domain.exception;

public class InvalidEmailException extends RuntimeException {
  public InvalidEmailException(String message) {
    super(message);
  }

  public InvalidEmailException() {
    super();
  }

  public InvalidEmailException(String message, Throwable cause) {
    super(message, cause);
  }
}
