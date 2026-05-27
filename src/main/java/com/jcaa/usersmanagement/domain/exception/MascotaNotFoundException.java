package com.jcaa.usersmanagement.domain.exception;

public final class MascotaNotFoundException extends DomainException {

  private static final String MESSAGE_BY_ID = "The mascota with id '%s' was not found.";

  private MascotaNotFoundException(final String message) {
    super(message);
  }

  public static MascotaNotFoundException becauseIdWasNotFound(final String id) {
    return new MascotaNotFoundException(String.format(MESSAGE_BY_ID, id));
  }
}
