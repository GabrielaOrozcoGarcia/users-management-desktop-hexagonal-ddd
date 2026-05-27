package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidMascotaIdException;
import java.util.Objects;

public record MascotaId(String value) {

  public MascotaId {
    final String normalized = Objects.requireNonNull(value, "MascotaId cannot be null").trim();
    if (normalized.isEmpty()) {
      throw InvalidMascotaIdException.becauseValueIsEmpty();
    }
    value = normalized;
  }

  @Override
  public String toString() { return value; }
}
