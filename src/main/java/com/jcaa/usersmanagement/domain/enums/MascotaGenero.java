package com.jcaa.usersmanagement.domain.enums;

import com.jcaa.usersmanagement.domain.exception.InvalidMascotaGeneroException;

public enum MascotaGenero {
  MACHO,
  HEMBRA;

  public static MascotaGenero fromString(final String value) {
    for (final MascotaGenero genero : values()) {
      if (genero.name().equalsIgnoreCase(value)) {
        return genero;
      }
    }
    throw InvalidMascotaGeneroException.becauseValueIsInvalid(value);
  }
}
