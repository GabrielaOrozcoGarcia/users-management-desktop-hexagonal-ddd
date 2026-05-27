package com.jcaa.usersmanagement.domain.exception;

public final class InvalidMascotaVeterinarioException extends DomainException {

  private static final String MESSAGE_EMPTY = "The mascota veterinario must not be empty.";

  private InvalidMascotaVeterinarioException(final String message) { super(message); }

  public static InvalidMascotaVeterinarioException becauseValueIsEmpty() {
    return new InvalidMascotaVeterinarioException(MESSAGE_EMPTY);
  }
}
