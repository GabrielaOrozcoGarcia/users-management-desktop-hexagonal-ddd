package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidMascotaNombreException;
import java.util.Objects;

public record MascotaNombre(String value) {

  private static final int MINIMUM_LENGTH = 2;

  public MascotaNombre {
    final String normalized = Objects.requireNonNull(value, "MascotaNombre cannot be null").trim();
    if (normalized.isEmpty()) {
      throw InvalidMascotaNombreException.becauseValueIsEmpty();
    }
    if (normalized.length() < MINIMUM_LENGTH) {
      throw InvalidMascotaNombreException.becauseLengthIsTooShort(MINIMUM_LENGTH);
    }
    value = normalized;
  }

  @Override
  public String toString() { return value; }
}
