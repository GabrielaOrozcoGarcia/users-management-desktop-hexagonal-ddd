package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidMascotaFechaNacimientoException;
import java.util.Objects;
import java.util.regex.Pattern;

public record MascotaFechaNacimiento(String value) {

  private static final Pattern DATE_PATTERN =
      Pattern.compile("^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$");

  public MascotaFechaNacimiento {
    final String normalized = Objects.requireNonNull(value, "MascotaFechaNacimiento cannot be null").trim();
    if (normalized.isEmpty()) {
      throw InvalidMascotaFechaNacimientoException.becauseValueIsEmpty();
    }
    if (!DATE_PATTERN.matcher(normalized).matches()) {
      throw InvalidMascotaFechaNacimientoException.becauseFormatIsInvalid(normalized);
    }
    value = normalized;
  }

  @Override
  public String toString() { return value; }
}
