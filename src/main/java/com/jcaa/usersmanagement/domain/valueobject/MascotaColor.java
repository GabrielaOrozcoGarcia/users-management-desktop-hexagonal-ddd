package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidMascotaColorException;
import java.util.Objects;

public record MascotaColor(String value) {

  public MascotaColor {
    final String normalized = Objects.requireNonNull(value, "MascotaColor cannot be null").trim();
    if (normalized.isEmpty()) {
      throw InvalidMascotaColorException.becauseValueIsEmpty();
    }
    value = normalized;
  }

  @Override
  public String toString() { return value; }
}
