package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.menu;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Getter
@RequiredArgsConstructor
public enum AppMenuOption {

  MANAGE_USERS   (1, "Gestionar Usuarios"),
  MANAGE_MASCOTAS(2, "Gestionar Mascotas"),
  EXIT           (0, "Salir");

  private final int number;
  private final String description;

  public static Optional<AppMenuOption> fromNumber(final int number) {
    for (final AppMenuOption option : values()) {
      if (option.number == number) {
        return Optional.of(option);
      }
    }
    return Optional.empty();
  }
}
