package com.jcaa.usersmanagement.domain.exception;

public final class InvalidMascotaRazaException extends DomainException {

  private static final String MESSAGE_EMPTY = "The mascota raza must not be empty.";

  private InvalidMascotaRazaException(final String message) { super(message); }

  public static InvalidMascotaRazaException becauseValueIsEmpty() {
    return new InvalidMascotaRazaException(MESSAGE_EMPTY);
  }
}
