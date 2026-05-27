package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.domain.exception.MascotaNotFoundException;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.MascotaResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.MascotaController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.MascotaResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class FindMascotaByIdHandler implements OperationHandler {

  private final MascotaController mascotaController;
  private final ConsoleIO console;
  private final MascotaResponsePrinter printer;

  @Override
  public void handle() {
    final String id = console.readRequired("ID Mascota: ");
    try {
      final MascotaResponse mascota = mascotaController.findMascotaById(id);
      printer.print(mascota);
    } catch (final MascotaNotFoundException ex) {
      console.println("  No encontrada: " + ex.getMessage());
    }
  }
}
