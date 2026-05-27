package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.menu.AppMenuOption;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.MascotaController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.UserController;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public final class ApplicationCli {

  private static final String BANNER =
      """
      ============================================
        Sistema de Gestion - Arquitectura Hexagonal
      ============================================""";

  private static final String MENU_BORDER = "  ============================================";

  private final UserController userController;
  private final MascotaController mascotaController;
  private final ConsoleIO console;

  public void start() {
    console.println(BANNER);
    boolean running = true;
    while (running) {
      printMenu();
      final int choice = console.readInt("\n  Opcion: ");
      final Optional<AppMenuOption> option = AppMenuOption.fromNumber(choice);

      if (option.isEmpty()) {
        console.println("  Opcion invalida. Intente de nuevo.");
      } else {
        switch (option.get()) {
          case MANAGE_USERS    -> new UserManagementCli(userController, console).start();
          case MANAGE_MASCOTAS -> new MascotaManagementCli(mascotaController, console).start();
          case EXIT -> {
            console.println("\n  Hasta luego!\n");
            running = false;
          }
        }
      }
    }
  }

  private void printMenu() {
    console.println();
    console.println(MENU_BORDER);
    console.println("    Menu Principal");
    console.println(MENU_BORDER);
    for (final AppMenuOption option : AppMenuOption.values()) {
      console.printf("    [%d] %s%n", option.getNumber(), option.getDescription());
    }
    console.println(MENU_BORDER);
  }
}
