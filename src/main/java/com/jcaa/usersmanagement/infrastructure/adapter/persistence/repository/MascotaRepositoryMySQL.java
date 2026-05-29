package com.jcaa.usersmanagement.infrastructure.adapter.persistence.repository;

import com.jcaa.usersmanagement.application.port.out.*;
import com.jcaa.usersmanagement.domain.exception.MascotaNotFoundException;
import com.jcaa.usersmanagement.domain.model.MascotaModel;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.MascotaPersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.exception.PersistenceException;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper.MascotaPersistenceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@Log
@RequiredArgsConstructor
public class MascotaRepositoryMySQL
        implements SaveMascotaPort,
        UpdateMascotaPort,
        GetMascotaByIdPort,
        GetAllMascotasPort,
        DeleteMascotaPort {

  private static final String COLUMNS =
          "id, nombre, genero, peso, tamano, color, raza, especie, "
                  + "fecha_nacimiento, propietario, tipo, tiene_vacunas, veterinario, "
                  + "created_at, updated_at";

  private static final String SQL_INSERT =
          "INSERT INTO mascotas "
                  + "(id, nombre, genero, peso, tamano, color, raza, especie, "
                  + "fecha_nacimiento, propietario, tipo, tiene_vacunas, veterinario, "
                  + "created_at, updated_at) "
                  + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW())";

  private static final String SQL_UPDATE =
          "UPDATE mascotas SET "
                  + "nombre = ?, genero = ?, peso = ?, tamano = ?, color = ?, raza = ?, "
                  + "especie = ?, fecha_nacimiento = ?, propietario = ?, tipo = ?, "
                  + "tiene_vacunas = ?, veterinario = ?, updated_at = NOW() "
                  + "WHERE id = ?";

  private static final String SQL_SELECT_BY_ID =
          "SELECT " + COLUMNS + " FROM mascotas WHERE id = ? LIMIT 1";

  private static final String SQL_SELECT_ALL =
          "SELECT " + COLUMNS + " FROM mascotas ORDER BY nombre ASC";

  private static final String SQL_DELETE =
          "DELETE FROM mascotas WHERE id = ?";

  // ✅ DataSource en vez de Connection — pide conexión fresca en cada operación
  private final DataSource dataSource;

  @Override
  public MascotaModel save(final MascotaModel mascota) {
    executeSave(MascotaPersistenceMapper.fromModelToDto(mascota));
    return findByIdOrFail(mascota.getId().value());
  }

  @Override
  public MascotaModel update(final MascotaModel mascota) {
    executeUpdate(MascotaPersistenceMapper.fromModelToDto(mascota));
    return findByIdOrFail(mascota.getId().value());
  }

  @Override
  public Optional<MascotaModel> getById(final String id) {
    try (final Connection connection = dataSource.getConnection();
         final PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_BY_ID)) {
      stmt.setString(1, id);
      final ResultSet rs = stmt.executeQuery();
      if (!rs.next()) return Optional.empty();
      return Optional.of(MascotaPersistenceMapper.fromResultSetToModel(rs));
    } catch (final SQLException ex) {
      throw PersistenceException.becauseFindByIdFailed(id, ex);
    }
  }

  @Override
  public List<MascotaModel> getAll() {
    try (final Connection connection = dataSource.getConnection();
         final PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_ALL)) {
      return MascotaPersistenceMapper.fromResultSetToModelList(stmt.executeQuery());
    } catch (final SQLException ex) {
      throw PersistenceException.becauseFindAllFailed(ex);
    }
  }

  @Override
  public void delete(final String id) {
    try (final Connection connection = dataSource.getConnection();
         final PreparedStatement stmt = connection.prepareStatement(SQL_DELETE)) {
      stmt.setString(1, id);
      stmt.executeUpdate();
    } catch (final SQLException ex) {
      throw PersistenceException.becauseDeleteFailed(id, ex);
    }
  }

  // ─── private helpers ────────────────────────────────────────────────────────

  private void executeSave(final MascotaPersistenceDto dto) {
    try (final Connection connection = dataSource.getConnection();
         final PreparedStatement stmt = connection.prepareStatement(SQL_INSERT)) {
      stmt.setString (1,  dto.id());
      stmt.setString (2,  dto.nombre());
      stmt.setString (3,  dto.genero());
      stmt.setDouble (4,  dto.peso());
      stmt.setString (5,  dto.tamano());
      stmt.setString (6,  dto.color());
      stmt.setString (7,  dto.raza());
      stmt.setString (8,  dto.especie());
      stmt.setString (9,  dto.fechaNacimiento());
      stmt.setString (10, dto.propietario());
      stmt.setString (11, dto.tipo());
      stmt.setBoolean(12, dto.tieneVacunas());
      stmt.setString (13, dto.veterinario());
      stmt.executeUpdate();
    } catch (final SQLException ex) {
      throw PersistenceException.becauseSaveFailed(dto.id(), ex);
    }
  }

  private void executeUpdate(final MascotaPersistenceDto dto) {
    try (final Connection connection = dataSource.getConnection();
         final PreparedStatement stmt = connection.prepareStatement(SQL_UPDATE)) {
      stmt.setString (1,  dto.nombre());
      stmt.setString (2,  dto.genero());
      stmt.setDouble (3,  dto.peso());
      stmt.setString (4,  dto.tamano());
      stmt.setString (5,  dto.color());
      stmt.setString (6,  dto.raza());
      stmt.setString (7,  dto.especie());
      stmt.setString (8,  dto.fechaNacimiento());
      stmt.setString (9,  dto.propietario());
      stmt.setString (10, dto.tipo());
      stmt.setBoolean(11, dto.tieneVacunas());
      stmt.setString (12, dto.veterinario());
      stmt.setString (13, dto.id());
      stmt.executeUpdate();
    } catch (final SQLException ex) {
      throw PersistenceException.becauseUpdateFailed(dto.id(), ex);
    }
  }

  private MascotaModel findByIdOrFail(final String id) {
    return getById(id)
            .orElseThrow(() -> MascotaNotFoundException.becauseIdWasNotFound(id));
  }
}