package com.jcaa.usersmanagement.domain.exception;

public final class InvalidMascotaFechaNacimientoException extends DomainException {

  private static final String MESSAGE_EMPTY          = "The mascota fechaNacimiento must not be empty.";
  private static final String MESSAGE_INVALID_FORMAT =
      "The mascota fechaNacimiento format is invalid: '%s'. Expected YYYY-MM-DD.";

  private InvalidMascotaFechaNacimientoException(final String message) { super(message); }

  public static InvalidMascotaFechaNacimientoException becauseValueIsEmpty() {
    return new InvalidMascotaFechaNacimientoException(MESSAGE_EMPTY);
  }

  public static InvalidMascotaFechaNacimientoException becauseFormatIsInvalid(final String value) {
    return new InvalidMascotaFechaNacimientoException(
        String.format(MESSAGE_INVALID_FORMAT, value));
  }
}
