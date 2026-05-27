package com.jcaa.usersmanagement.domain.exception;

public final class InvalidMascotaEspecieException extends DomainException {

  private static final String MESSAGE_EMPTY = "The mascota especie must not be empty.";

  private InvalidMascotaEspecieException(final String message) { super(message); }

  public static InvalidMascotaEspecieException becauseValueIsEmpty() {
    return new InvalidMascotaEspecieException(MESSAGE_EMPTY);
  }
}
