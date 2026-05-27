package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidMascotaPropietarioException;
import java.util.Objects;

public record MascotaPropietario(String value) {

  public MascotaPropietario {
    final String normalized = Objects.requireNonNull(value, "MascotaPropietario cannot be null").trim();
    if (normalized.isEmpty()) {
      throw InvalidMascotaPropietarioException.becauseValueIsEmpty();
    }
    value = normalized;
  }

  @Override
  public String toString() { return value; }
}
