package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto;

public record UpdateMascotaRequest(
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
    String  tipo,
    Boolean tieneVacunas,
    String  veterinario) {}
