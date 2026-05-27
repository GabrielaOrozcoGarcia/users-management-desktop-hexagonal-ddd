package com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto;

public record MascotaPersistenceDto(
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
    boolean tieneVacunas,
    String  veterinario,
    String  createdAt,
    String  updatedAt) {}
