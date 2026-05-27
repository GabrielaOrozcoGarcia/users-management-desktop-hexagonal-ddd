package com.jcaa.usersmanagement.domain.exception;

public final class InvalidMascotaPesoException extends DomainException {

  private static final String MESSAGE_NOT_POSITIVE =
      "The mascota peso must be a positive number, but was: %s.";

  private InvalidMascotaPesoException(final String message) { super(message); }

  public static InvalidMascotaPesoException becauseValueIsNotPositive(final double value) {
    return new InvalidMascotaPesoException(String.format(MESSAGE_NOT_POSITIVE, value));
  }
}
