package com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper;

import com.jcaa.usersmanagement.domain.enums.MascotaGenero;
import com.jcaa.usersmanagement.domain.enums.MascotaTamano;
import com.jcaa.usersmanagement.domain.enums.MascotaTipo;
import com.jcaa.usersmanagement.domain.model.MascotaModel;
import com.jcaa.usersmanagement.domain.valueobject.*;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.MascotaPersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity.MascotaEntity;
import lombok.experimental.UtilityClass;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class MascotaPersistenceMapper {

  /** Modelo → DTO listo para INSERT/UPDATE */
  public MascotaPersistenceDto fromModelToDto(final MascotaModel m) {
    return new MascotaPersistenceDto(
        m.getId().value(),
        m.getNombre().value(),
        m.getGenero().name(),
        m.getPeso().value(),
        m.getTamano().name(),
        m.getColor().value(),
        m.getRaza().value(),
        m.getEspecie().value(),
        m.getFechaNacimiento().value(),
        m.getPropietario().value(),
        m.getTipo().name(),
        m.getTieneVacunas().value(),
        m.getVeterinario().value(),
        null,
        null);
  }

  /** ResultSet → Entity (nombres de columna de la tabla mascotas) */
  public MascotaEntity fromResultSetToEntity(final ResultSet rs) throws SQLException {
    return new MascotaEntity(
        rs.getString("id"),
        rs.getString("nombre"),
        rs.getString("genero"),
        rs.getDouble("peso"),
        rs.getString("tamano"),
        rs.getString("color"),
        rs.getString("raza"),
        rs.getString("especie"),
        rs.getString("fecha_nacimiento"),
        rs.getString("propietario"),
        rs.getString("tipo"),
        rs.getBoolean("tiene_vacunas"),
        rs.getString("veterinario"),
        rs.getString("created_at"),
        rs.getString("updated_at"));
  }

  /** Entity → Modelo (reconstruye VOs y Enums) */
  public MascotaModel fromEntityToModel(final MascotaEntity e) {
    return new MascotaModel(
        new MascotaId(e.id()),
        new MascotaNombre(e.nombre()),
        MascotaGenero.fromString(e.genero()),
        new MascotaPeso(e.peso()),
        MascotaTamano.fromString(e.tamano()),
        new MascotaColor(e.color()),
        new MascotaRaza(e.raza()),
        new MascotaEspecie(e.especie()),
        new MascotaFechaNacimiento(e.fechaNacimiento()),
        new MascotaPropietario(e.propietario()),
        MascotaTipo.fromString(e.tipo()),
        MascotaVacunas.of(e.tieneVacunas()),
        new MascotaVeterinario(e.veterinario()));
  }

  public MascotaModel fromResultSetToModel(final ResultSet rs) throws SQLException {
    return fromEntityToModel(fromResultSetToEntity(rs));
  }

  public List<MascotaModel> fromResultSetToModelList(final ResultSet rs) throws SQLException {
    final List<MascotaModel> mascotas = new ArrayList<>();
    while (rs.next()) {
      mascotas.add(fromResultSetToModel(rs));
    }
    return mascotas;
  }
}
