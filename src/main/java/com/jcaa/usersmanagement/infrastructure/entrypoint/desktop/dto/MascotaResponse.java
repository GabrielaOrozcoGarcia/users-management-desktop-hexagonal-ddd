package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto;

public record MascotaResponse(
    String  id,
    String  nombre,
    String  genero,
    double  peso,
    String  tamano,
    String  color,
    String  raza,
    String  especie,
    String  fechaNacimiento,
    String  propietario,
    String  tipo,          // DOMESTICO | SALVAJE
    boolean tieneVacunas,
    String  veterinario) {}
