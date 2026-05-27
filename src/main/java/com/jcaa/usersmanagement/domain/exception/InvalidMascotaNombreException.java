package com.jcaa.usersmanagement.domain.exception;

public final class InvalidMascotaNombreException extends DomainException {

  private static final String MESSAGE_EMPTY     = "The mascota nombre must not be empty.";
  private static final String MESSAGE_TOO_SHORT = "The mascota nombre must have at least %d characters.";

  private InvalidMascotaNombreException(final String message) { super(message); }

  public static InvalidMascotaNombreException becauseValueIsEmpty() {
    return new InvalidMascotaNombreException(MESSAGE_EMPTY);
  }

  public static InvalidMascotaNombreException becauseLengthIsTooShort(final int min) {
    return new InvalidMascotaNombreException(String.format(MESSAGE_TOO_SHORT, min));
  }
}
