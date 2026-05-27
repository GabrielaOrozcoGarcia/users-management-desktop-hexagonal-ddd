package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.*;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.MascotaResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.menu.MascotaMenuOption;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.MascotaController;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public final class MascotaManagementCli {

  private static final String BANNER =
      """
      ==========================================
           Mascotas Management System
      ==========================================""";

  private static final String MENU_BORDER = "  ==========================================";

  private final MascotaController mascotaController;
  private final ConsoleIO console;

  public void start() {
    console.println(BANNER);
    final MascotaResponsePrinter printer = new MascotaResponsePrinter(console);
    runLoop(buildHandlers(printer));
  }

  private void runLoop(final Map<MascotaMenuOption, OperationHandler> handlers) {
    boolean running = true;
    while (running) {
      printMenu();
      final int choice = console.readInt("\n  Opcion: ");
      final Optional<MascotaMenuOption> option = MascotaMenuOption.fromNumber(choice);

      if (option.isEmpty()) {
        console.println("  Opcion invalida. Intente de nuevo.");
      } else if (option.get() == MascotaMenuOption.EXIT) {
        console.println("\n  Volviendo al menu principal...\n");
        running = false;
      } else {
        executeHandler(handlers, option.get());
      }
    }
  }

  private void executeHandler(
      final Map<MascotaMenuOption, OperationHandler> handlers, final MascotaMenuOption option) {
    try {
      handlers.get(option).handle();
    } catch (final ConstraintViolationException ex) {
      console.println("  Errores de validacion:");
      ex.getConstraintViolations()
          .forEach(v -> console.println("    - " + v.getMessage()));
    } catch (final RuntimeException ex) {
      console.println("  Error inesperado: " + ex.getMessage());
    }
  }

  private Map<MascotaMenuOption, OperationHandler> buildHandlers(final MascotaResponsePrinter printer) {
    return Map.of(
        MascotaMenuOption.LIST_MASCOTAS,  new ListMascotasHandler(mascotaController, printer),
        MascotaMenuOption.FIND_MASCOTA,   new FindMascotaByIdHandler(mascotaController, console, printer),
        MascotaMenuOption.CREATE_MASCOTA, new CreateMascotaHandler(mascotaController, console, printer),
        MascotaMenuOption.UPDATE_MASCOTA, new UpdateMascotaHandler(mascotaController, console, printer),
        MascotaMenuOption.DELETE_MASCOTA, new DeleteMascotaHandler(mascotaController, console));
  }

  private void printMenu() {
    console.println();
    console.println(MENU_BORDER);
    console.println("    Menu Mascotas");
    console.println(MENU_BORDER);
    for (final MascotaMenuOption option : MascotaMenuOption.values()) {
      console.printf("    [%d] %s%n", option.getNumber(), option.getDescription());
    }
    console.println(MENU_BORDER);
  }
}
