package com.jcaa.usersmanagement.application.service.dto.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateMascotaCommand(
    @NotBlank(message = "id must not be blank")              String  id,
    @NotBlank(message = "nombre must not be blank")          String  nombre,
    @NotBlank(message = "genero must not be blank")          String  genero,
    @Positive(message = "peso must be positive")             double  peso,
    @NotBlank(message = "tamano must not be blank")          String  tamano,
    @NotBlank(message = "color must not be blank")           String  color,
    @NotBlank(message = "raza must not be blank")            String  raza,
    @NotBlank(message = "especie must not be blank")         String  especie,
    @NotBlank(message = "fechaNacimiento must not be blank") String  fechaNacimiento,
    @NotBlank(message = "propietario must not be blank")     String  propietario,
    @NotBlank(message = "tipo must not be blank")            String  tipo,
    @NotNull (message = "tieneVacunas must not be null")     Boolean tieneVacunas,
    @NotBlank(message = "veterinario must not be blank")     String  veterinario) {
}
