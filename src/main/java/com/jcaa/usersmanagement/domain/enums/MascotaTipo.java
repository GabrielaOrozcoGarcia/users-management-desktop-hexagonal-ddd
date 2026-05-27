package com.jcaa.usersmanagement.domain.enums;

import com.jcaa.usersmanagement.domain.exception.InvalidMascotaTipoException;

/** Indica si la mascota es doméstica o salvaje. Reemplaza el campo boolean domesticoOSalvaje. */
public enum MascotaTipo {
  DOMESTICO,
  SALVAJE;

  public static MascotaTipo fromString(final String value) {
    for (final MascotaTipo tipo : values()) {
      if (tipo.name().equalsIgnoreCase(value)) {
        return tipo;
      }
    }
    throw InvalidMascotaTipoException.becauseValueIsInvalid(value);
  }

  public static MascotaTipo fromBoolean(final boolean domestico) {
    return domestico ? DOMESTICO : SALVAJE;
  }

  public boolean toDomesticoBoolean() {
    return this == DOMESTICO;
  }
}
