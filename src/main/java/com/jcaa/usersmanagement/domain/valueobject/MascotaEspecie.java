package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidMascotaEspecieException;
import java.util.Objects;

public record MascotaEspecie(String value) {

  public MascotaEspecie {
    final String normalized = Objects.requireNonNull(value, "MascotaEspecie cannot be null").trim();
    if (normalized.isEmpty()) {
      throw InvalidMascotaEspecieException.becauseValueIsEmpty();
    }
    value = normalized;
  }

  @Override
  public String toString() { return value; }
}
