package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.MascotaResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.MascotaController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateMascotaRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.MascotaResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class CreateMascotaHandler implements OperationHandler {

  private final MascotaController mascotaController;
  private final ConsoleIO console;
  private final MascotaResponsePrinter printer;

  @Override
  public void handle() {
    final String  id              = console.readRequired("ID                                       : ");
    final String  nombre          = console.readRequired("Nombre                                   : ");
    final String  genero          = console.readRequired("Genero (MACHO / HEMBRA)                  : ");
    final double  peso            = console.readDouble  ("Peso en kg  (ej: 12.5)                   : ");
    final String  tamano          = console.readRequired("Tamano (PEQUENO / MEDIANO / GRANDE)       : ");
    final String  color           = console.readRequired("Color                                    : ");
    final String  raza            = console.readRequired("Raza                                     : ");
    final String  especie         = console.readRequired("Especie  (Perro / Gato / Ave / Reptil...) : ");
    final String  fechaNacimiento = console.readRequired("Fecha Nacimiento (YYYY-MM-DD)             : ");
    final String  propietario     = console.readRequired("ID Propietario                           : ");
    final String  tipo            = console.readRequired("Tipo (DOMESTICO / SALVAJE)               : ");
    final boolean tieneVacunas    = console.readBoolean ("Tiene Vacunas? (S / N)                   : ");
    final String  veterinario     = console.readRequired("Veterinario                              : ");

    final MascotaResponse created = mascotaController.createMascota(
        new CreateMascotaRequest(id, nombre, genero.toUpperCase(), peso,
            tamano.toUpperCase(), color, raza, especie, fechaNacimiento,
            propietario, tipo.toUpperCase(), tieneVacunas, veterinario));
    console.println("\n  Mascota creada exitosamente.");
    printer.print(created);
  }
}
