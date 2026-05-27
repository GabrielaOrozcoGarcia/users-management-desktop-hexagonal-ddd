package com.jcaa.usersmanagement.domain.exception;

public final class InvalidMascotaPropietarioException extends DomainException {

  private static final String MESSAGE_EMPTY = "The mascota propietario must not be empty.";

  private InvalidMascotaPropietarioException(final String message) { super(message); }

  public static InvalidMascotaPropietarioException becauseValueIsEmpty() {
    return new InvalidMascotaPropietarioException(MESSAGE_EMPTY);
  }
}
