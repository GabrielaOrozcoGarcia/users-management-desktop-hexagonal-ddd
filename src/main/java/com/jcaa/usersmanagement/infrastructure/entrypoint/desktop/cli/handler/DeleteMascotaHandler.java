package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.domain.exception.MascotaNotFoundException;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.MascotaController;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class DeleteMascotaHandler implements OperationHandler {

  private final MascotaController mascotaController;
  private final ConsoleIO console;

  @Override
  public void handle() {
    final String id = console.readRequired("ID Mascota a eliminar: ");
    try {
      mascotaController.deleteMascota(id);
      console.println("  Mascota eliminada exitosamente.");
    } catch (final MascotaNotFoundException ex) {
      console.println("  No encontrada: " + ex.getMessage());
    }
  }
}
