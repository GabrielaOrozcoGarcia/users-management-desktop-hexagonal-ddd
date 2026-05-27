package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidMascotaVeterinarioException;
import java.util.Objects;

public record MascotaVeterinario(String value) {

  public MascotaVeterinario {
    final String normalized = Objects.requireNonNull(value, "MascotaVeterinario cannot be null").trim();
    if (normalized.isEmpty()) {
      throw InvalidMascotaVeterinarioException.becauseValueIsEmpty();
    }
    value = normalized;
  }

  @Override
  public String toString() { return value; }
}
