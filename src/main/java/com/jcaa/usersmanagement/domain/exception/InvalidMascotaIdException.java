package com.jcaa.usersmanagement.domain.exception;

public final class InvalidMascotaIdException extends DomainException {

  private static final String MESSAGE_EMPTY = "The mascota id must not be empty.";

  private InvalidMascotaIdException(final String message) { super(message); }

  public static InvalidMascotaIdException becauseValueIsEmpty() {
    return new InvalidMascotaIdException(MESSAGE_EMPTY);
  }
}
