package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.MascotaResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public final class MascotaResponsePrinter {

  private static final String SEPARATOR = "-".repeat(60);
  private static final String ROW_FORMAT = "  %-22s : %s%n";

  private final ConsoleIO console;

  public void print(final MascotaResponse r) {
    console.println(SEPARATOR);
    console.printf(ROW_FORMAT, "ID",               r.id());
    console.printf(ROW_FORMAT, "Nombre",            r.nombre());
    console.printf(ROW_FORMAT, "Genero",            r.genero());
    console.printf(ROW_FORMAT, "Peso (kg)",         String.valueOf(r.peso()));
    console.printf(ROW_FORMAT, "Tamano",            r.tamano());
    console.printf(ROW_FORMAT, "Color",             r.color());
    console.printf(ROW_FORMAT, "Raza",              r.raza());
    console.printf(ROW_FORMAT, "Especie",           r.especie());
    console.printf(ROW_FORMAT, "Fecha Nacimiento",  r.fechaNacimiento());
    console.printf(ROW_FORMAT, "Propietario",       r.propietario());
    console.printf(ROW_FORMAT, "Tipo",              r.tipo());
    console.printf(ROW_FORMAT, "Tiene Vacunas",     r.tieneVacunas() ? "Si" : "No");
    console.printf(ROW_FORMAT, "Veterinario",       r.veterinario());
    console.println(SEPARATOR);
  }

  public void printList(final List<MascotaResponse> mascotas) {
    if (mascotas.isEmpty()) {
      console.println("  No se encontraron mascotas.");
      return;
    }
    console.printf("%n  Total: %d mascota(s)%n", mascotas.size());
    mascotas.forEach(this::print);
  }
}
