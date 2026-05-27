package com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity;

public record MascotaEntity(
    String  id,
    String  nombre,
    String  genero,        // MascotaGenero.name()
    double  peso,
    String  tamano,        // MascotaTamano.name()
    String  color,
    String  raza,
    String  especie,
    String  fechaNacimiento,
    String  propietario,
    String  tipo,          // MascotaTipo.name()  (DOMESTICO | SALVAJE)
    boolean tieneVacunas,
    String  veterinario,
    String  createdAt,
    String  updatedAt) {}
