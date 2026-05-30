package com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import com.jcaa.usersmanagement.domain.enums.MascotaGenero;
import com.jcaa.usersmanagement.domain.enums.MascotaTamano;
import com.jcaa.usersmanagement.domain.enums.MascotaTipo;
import com.jcaa.usersmanagement.domain.model.MascotaModel;
import com.jcaa.usersmanagement.domain.valueobject.*;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.MascotaPersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity.MascotaEntity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Tests para MascotaPersistenceMapper.
 *
 * <p>Cubre: fromModelToDto(), fromEntityToModel(), fromResultSetToEntity() (flujo feliz y
 * propagación de SQLException), fromResultSetToModel() y fromResultSetToModelList()
 * (lista vacía, múltiples filas y propagación de SQLException).
 */
@DisplayName("MascotaPersistenceMapper")
@ExtendWith(MockitoExtension.class)
class MascotaPersistenceMapperTest {

    private static final String ID              = "masc-001";
    private static final String NOMBRE          = "Firulais";
    private static final String GENERO          = "MACHO";
    private static final double PESO            = 8.5;
    private static final String TAMANO          = "MEDIANO";
    private static final String COLOR           = "Cafe";
    private static final String RAZA            = "Labrador";
    private static final String ESPECIE         = "Perro";
    private static final String FECHA_NAC       = "2020-03-15";
    private static final String PROPIETARIO     = "usr-001";
    private static final String TIPO            = "DOMESTICO";
    private static final boolean TIENE_VACUNAS  = true;
    private static final String VETERINARIO     = "Dr. Martinez";
    private static final String CREATED_AT      = "2024-01-01 00:00:00";
    private static final String UPDATED_AT      = "2024-01-02 00:00:00";

    @Mock private ResultSet resultSet;

    private MascotaModel mascotaModel;
    private MascotaEntity mascotaEntity;

    @BeforeEach
    void setUp() {
        mascotaModel = MascotaModel.create(
                new MascotaId(ID),
                new MascotaNombre(NOMBRE),
                MascotaGenero.MACHO,
                new MascotaPeso(PESO),
                MascotaTamano.MEDIANO,
                new MascotaColor(COLOR),
                new MascotaRaza(RAZA),
                new MascotaEspecie(ESPECIE),
                new MascotaFechaNacimiento(FECHA_NAC),
                new MascotaPropietario(PROPIETARIO),
                MascotaTipo.DOMESTICO,
                MascotaVacunas.of(TIENE_VACUNAS),
                new MascotaVeterinario(VETERINARIO));

        mascotaEntity = new MascotaEntity(
                ID, NOMBRE, GENERO, PESO, TAMANO, COLOR, RAZA, ESPECIE,
                FECHA_NAC, PROPIETARIO, TIPO, TIENE_VACUNAS, VETERINARIO,
                CREATED_AT, UPDATED_AT);
    }

    // ── fromModelToDto() ─────────────────────────────────────────────────────────

    @Test
    @DisplayName("fromModelToDto() mapea todos los campos y deja timestamps en null")
    void shouldMapModelToDto() {
        final MascotaPersistenceDto result = MascotaPersistenceMapper.fromModelToDto(mascotaModel);

        assertAll(
                "fromModelToDto()",
                () -> assertEquals(ID,          result.id(),             "id"),
                () -> assertEquals(NOMBRE,      result.nombre(),         "nombre"),
                () -> assertEquals(GENERO,      result.genero(),         "genero"),
                () -> assertEquals(PESO,        result.peso(),           "peso"),
                () -> assertEquals(TAMANO,      result.tamano(),         "tamano"),
                () -> assertEquals(COLOR,       result.color(),          "color"),
                () -> assertEquals(RAZA,        result.raza(),           "raza"),
                () -> assertEquals(ESPECIE,     result.especie(),        "especie"),
                () -> assertEquals(FECHA_NAC,   result.fechaNacimiento(),"fechaNacimiento"),
                () -> assertEquals(PROPIETARIO, result.propietario(),    "propietario"),
                () -> assertEquals(TIPO,        result.tipo(),           "tipo"),
                () -> assertTrue(result.tieneVacunas(),                  "tieneVacunas"),
                () -> assertEquals(VETERINARIO, result.veterinario(),    "veterinario"),
                () -> assertNull(result.createdAt(),                     "createdAt debe ser null"),
                () -> assertNull(result.updatedAt(),                     "updatedAt debe ser null"));
    }

    // ── fromEntityToModel() ──────────────────────────────────────────────────────

    @Test
    @DisplayName("fromEntityToModel() reconstruye correctamente todos los VOs y Enums")
    void shouldMapEntityToModel() {
        final MascotaModel result = MascotaPersistenceMapper.fromEntityToModel(mascotaEntity);

        assertAll(
                "fromEntityToModel()",
                () -> assertEquals(ID,                result.getId().value(),            "id"),
                () -> assertEquals(NOMBRE,            result.getNombre().value(),        "nombre"),
                () -> assertEquals(MascotaGenero.MACHO,   result.getGenero(),            "genero"),
                () -> assertEquals(PESO,              result.getPeso().value(),           "peso"),
                () -> assertEquals(MascotaTamano.MEDIANO, result.getTamano(),            "tamano"),
                () -> assertEquals(COLOR,             result.getColor().value(),          "color"),
                () -> assertEquals(RAZA,              result.getRaza().value(),           "raza"),
                () -> assertEquals(ESPECIE,           result.getEspecie().value(),        "especie"),
                () -> assertEquals(FECHA_NAC,         result.getFechaNacimiento().value(),"fechaNacimiento"),
                () -> assertEquals(PROPIETARIO,       result.getPropietario().value(),    "propietario"),
                () -> assertEquals(MascotaTipo.DOMESTICO, result.getTipo(),              "tipo"),
                () -> assertTrue(result.getTieneVacunas().value(),                        "tieneVacunas"),
                () -> assertEquals(VETERINARIO,       result.getVeterinario().value(),    "veterinario"));
    }

    // ── fromResultSetToEntity() — flujo feliz ────────────────────────────────────

    @Test
    @DisplayName("fromResultSetToEntity() lee todas las columnas del ResultSet correctamente")
    void shouldReadAllColumnsFromResultSet() throws SQLException {
        // Arrange
        when(resultSet.getString("id")).thenReturn(ID);
        when(resultSet.getString("nombre")).thenReturn(NOMBRE);
        when(resultSet.getString("genero")).thenReturn(GENERO);
        when(resultSet.getDouble("peso")).thenReturn(PESO);
        when(resultSet.getString("tamano")).thenReturn(TAMANO);
        when(resultSet.getString("color")).thenReturn(COLOR);
        when(resultSet.getString("raza")).thenReturn(RAZA);
        when(resultSet.getString("especie")).thenReturn(ESPECIE);
        when(resultSet.getString("fecha_nacimiento")).thenReturn(FECHA_NAC);
        when(resultSet.getString("propietario")).thenReturn(PROPIETARIO);
        when(resultSet.getString("tipo")).thenReturn(TIPO);
        when(resultSet.getBoolean("tiene_vacunas")).thenReturn(TIENE_VACUNAS);
        when(resultSet.getString("veterinario")).thenReturn(VETERINARIO);
        when(resultSet.getString("created_at")).thenReturn(CREATED_AT);
        when(resultSet.getString("updated_at")).thenReturn(UPDATED_AT);

        // Act
        final MascotaEntity result = MascotaPersistenceMapper.fromResultSetToEntity(resultSet);

        // Assert
        assertAll(
                "fromResultSetToEntity()",
                () -> assertEquals(ID,          result.id()),
                () -> assertEquals(NOMBRE,      result.nombre()),
                () -> assertEquals(GENERO,      result.genero()),
                () -> assertEquals(PESO,        result.peso()),
                () -> assertEquals(TAMANO,      result.tamano()),
                () -> assertEquals(COLOR,       result.color()),
                () -> assertEquals(RAZA,        result.raza()),
                () -> assertEquals(ESPECIE,     result.especie()),
                () -> assertEquals(FECHA_NAC,   result.fechaNacimiento()),
                () -> assertEquals(PROPIETARIO, result.propietario()),
                () -> assertEquals(TIPO,        result.tipo()),
                () -> assertTrue(result.tieneVacunas()),
                () -> assertEquals(VETERINARIO, result.veterinario()),
                () -> assertEquals(CREATED_AT,  result.createdAt()),
                () -> assertEquals(UPDATED_AT,  result.updatedAt()));
    }

    // ── fromResultSetToEntity() — propagación de SQLException ───────────────────

    @Test
    @DisplayName("fromResultSetToEntity() propaga SQLException cuando falla la lectura")
    void shouldPropagateExceptionFromResultSet() throws SQLException {
        when(resultSet.getString(anyString())).thenThrow(new SQLException("Column read failed"));

        assertThrows(SQLException.class,
                () -> MascotaPersistenceMapper.fromResultSetToEntity(resultSet));
    }

    // ── fromResultSetToModelList() — lista vacía ─────────────────────────────────

    @Test
    @DisplayName("fromResultSetToModelList() retorna lista vacía cuando el ResultSet no tiene filas")
    void shouldReturnEmptyListWhenResultSetIsEmpty() throws SQLException {
        when(resultSet.next()).thenReturn(false);

        final List<MascotaModel> result =
                MascotaPersistenceMapper.fromResultSetToModelList(resultSet);

        assertTrue(result.isEmpty(), "debe retornar lista vacía");
    }

    // ── fromResultSetToModelList() — múltiples filas ─────────────────────────────

    @Test
    @DisplayName("fromResultSetToModelList() retorna un modelo por fila del ResultSet")
    void shouldReturnOneModelPerRow() throws SQLException {
        when(resultSet.next()).thenReturn(true, true, false);
        when(resultSet.getString("id")).thenReturn(ID, "masc-002");
        when(resultSet.getString("nombre")).thenReturn(NOMBRE, "Luna");
        when(resultSet.getString("genero")).thenReturn(GENERO, "HEMBRA");
        when(resultSet.getDouble("peso")).thenReturn(PESO, 3.2);
        when(resultSet.getString("tamano")).thenReturn(TAMANO, "PEQUENO");
        when(resultSet.getString("color")).thenReturn(COLOR, "Negro");
        when(resultSet.getString("raza")).thenReturn(RAZA, "Siames");
        when(resultSet.getString("especie")).thenReturn(ESPECIE, "Gato");
        when(resultSet.getString("fecha_nacimiento")).thenReturn(FECHA_NAC, "2022-07-10");
        when(resultSet.getString("propietario")).thenReturn(PROPIETARIO, "usr-002");
        when(resultSet.getString("tipo")).thenReturn(TIPO, "SALVAJE");
        when(resultSet.getBoolean("tiene_vacunas")).thenReturn(true, false);
        when(resultSet.getString("veterinario")).thenReturn(VETERINARIO, "Dra. Gomez");
        when(resultSet.getString("created_at")).thenReturn(CREATED_AT, CREATED_AT);
        when(resultSet.getString("updated_at")).thenReturn(UPDATED_AT, UPDATED_AT);

        final List<MascotaModel> result =
                MascotaPersistenceMapper.fromResultSetToModelList(resultSet);

        assertEquals(2, result.size(), "debe retornar un modelo por fila");
    }

    // ── fromResultSetToModelList() — propagación de SQLException ────────────────

    @Test
    @DisplayName("fromResultSetToModelList() propaga SQLException cuando falla la lectura de una fila")
    void shouldPropagateExceptionDuringIteration() throws SQLException {
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getString(anyString())).thenThrow(new SQLException("Row read failed"));

        assertThrows(SQLException.class,
                () -> MascotaPersistenceMapper.fromResultSetToModelList(resultSet));
    }
}
