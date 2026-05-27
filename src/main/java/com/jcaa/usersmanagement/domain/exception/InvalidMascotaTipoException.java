package com.jcaa.usersmanagement.domain.exception;

public final class InvalidMascotaTipoException extends DomainException {

  private static final String MESSAGE_INVALID =
      "The mascota tipo '%s' is not valid. Expected: DOMESTICO or SALVAJE.";

  private InvalidMascotaTipoException(final String message) { super(message); }

  public static InvalidMascotaTipoException becauseValueIsInvalid(final String value) {
    return new InvalidMascotaTipoException(String.format(MESSAGE_INVALID, value));
  }
}
