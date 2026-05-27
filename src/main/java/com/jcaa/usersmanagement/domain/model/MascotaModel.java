package com.jcaa.usersmanagement.domain.model;

import com.jcaa.usersmanagement.domain.enums.MascotaGenero;
import com.jcaa.usersmanagement.domain.enums.MascotaTamano;
import com.jcaa.usersmanagement.domain.enums.MascotaTipo;
import com.jcaa.usersmanagement.domain.valueobject.*;
import lombok.Value;

@Value
public class MascotaModel {

  MascotaId             id;
  MascotaNombre         nombre;
  MascotaGenero         genero;
  MascotaPeso           peso;
  MascotaTamano         tamano;
  MascotaColor          color;
  MascotaRaza           raza;
  MascotaEspecie        especie;
  MascotaFechaNacimiento fechaNacimiento;
  MascotaPropietario    propietario;
  MascotaTipo           tipo;         // DOMESTICO | SALVAJE
  MascotaVacunas        tieneVacunas;
  MascotaVeterinario    veterinario;

  public static MascotaModel create(
      final MascotaId             id,
      final MascotaNombre         nombre,
      final MascotaGenero         genero,
      final MascotaPeso           peso,
      final MascotaTamano         tamano,
      final MascotaColor          color,
      final MascotaRaza           raza,
      final MascotaEspecie        especie,
      final MascotaFechaNacimiento fechaNacimiento,
      final MascotaPropietario    propietario,
      final MascotaTipo           tipo,
      final MascotaVacunas        tieneVacunas,
      final MascotaVeterinario    veterinario) {
    return new MascotaModel(id, nombre, genero, peso, tamano, color, raza,
        especie, fechaNacimiento, propietario, tipo, tieneVacunas, veterinario);
  }

  /** Marca la mascota como vacunada. */
  public MascotaModel vacunar() {
    return new MascotaModel(id, nombre, genero, peso, tamano, color, raza,
        especie, fechaNacimiento, propietario, tipo, MascotaVacunas.conVacunas(), veterinario);
  }
}
