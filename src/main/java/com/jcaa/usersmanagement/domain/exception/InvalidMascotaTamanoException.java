package com.jcaa.usersmanagement.domain.exception;

public final class InvalidMascotaTamanoException extends DomainException {

  private static final String MESSAGE_INVALID =
      "The mascota tamano '%s' is not valid. Expected: PEQUENO, MEDIANO or GRANDE.";

  private InvalidMascotaTamanoException(final String message) { super(message); }

  public static InvalidMascotaTamanoException becauseValueIsInvalid(final String value) {
    return new InvalidMascotaTamanoException(String.format(MESSAGE_INVALID, value));
  }
}
