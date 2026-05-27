package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidMascotaRazaException;
import java.util.Objects;

public record MascotaRaza(String value) {

  public MascotaRaza {
    final String normalized = Objects.requireNonNull(value, "MascotaRaza cannot be null").trim();
    if (normalized.isEmpty()) {
      throw InvalidMascotaRazaException.becauseValueIsEmpty();
    }
    value = normalized;
  }

  @Override
  public String toString() { return value; }
}
