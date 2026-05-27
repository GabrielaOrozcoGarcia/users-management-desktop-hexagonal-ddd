package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.menu;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Getter
@RequiredArgsConstructor
public enum MascotaMenuOption {

  LIST_MASCOTAS (1, "Listar todas las mascotas"),
  FIND_MASCOTA  (2, "Buscar mascota por ID"),
  CREATE_MASCOTA(3, "Crear mascota"),
  UPDATE_MASCOTA(4, "Actualizar mascota"),
  DELETE_MASCOTA(5, "Eliminar mascota"),
  EXIT          (0, "Volver al menu principal");

  private final int number;
  private final String description;

  public static Optional<MascotaMenuOption> fromNumber(final int number) {
    for (final MascotaMenuOption option : values()) {
      if (option.number == number) {
        return Optional.of(option);
      }
    }
    return Optional.empty();
  }
}
