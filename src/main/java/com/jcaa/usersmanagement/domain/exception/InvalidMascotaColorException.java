package com.jcaa.usersmanagement.domain.exception;

public final class InvalidMascotaColorException extends DomainException {

  private static final String MESSAGE_EMPTY = "The mascota color must not be empty.";

  private InvalidMascotaColorException(final String message) { super(message); }

  public static InvalidMascotaColorException becauseValueIsEmpty() {
    return new InvalidMascotaColorException(MESSAGE_EMPTY);
  }
}
