package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.MascotaResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.MascotaController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.MascotaResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public final class ListMascotasHandler implements OperationHandler {

  private final MascotaController mascotaController;
  private final MascotaResponsePrinter printer;

  @Override
  public void handle() {
    final List<MascotaResponse> mascotas = mascotaController.listAllMascotas();
    printer.printList(mascotas);
  }
}
