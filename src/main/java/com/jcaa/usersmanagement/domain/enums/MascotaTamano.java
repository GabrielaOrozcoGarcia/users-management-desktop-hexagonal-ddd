package com.jcaa.usersmanagement.domain.enums;

import com.jcaa.usersmanagement.domain.exception.InvalidMascotaTamanoException;

public enum MascotaTamano {
  PEQUENO,
  MEDIANO,
  GRANDE;

  public static MascotaTamano fromString(final String value) {
    for (final MascotaTamano tamano : values()) {
      if (tamano.name().equalsIgnoreCase(value)) {
        return tamano;
      }
    }
    throw InvalidMascotaTamanoException.becauseValueIsInvalid(value);
  }
}
