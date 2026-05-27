package com.jcaa.usersmanagement.domain.exception;

public final class InvalidMascotaGeneroException extends DomainException {

  private static final String MESSAGE_INVALID = "The mascota genero '%s' is not valid. Expected: MACHO or HEMBRA.";

  private InvalidMascotaGeneroException(final String message) { super(message); }

  public static InvalidMascotaGeneroException becauseValueIsInvalid(final String value) {
    return new InvalidMascotaGeneroException(String.format(MESSAGE_INVALID, value));
  }
}
