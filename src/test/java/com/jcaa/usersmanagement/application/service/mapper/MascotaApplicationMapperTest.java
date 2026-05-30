package com.jcaa.usersmanagement.application.service.mapper;

import static org.junit.jupiter.api.Assertions.*;

import com.jcaa.usersmanagement.application.service.dto.command.CreateMascotaCommand;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteMascotaCommand;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateMascotaCommand;
import com.jcaa.usersmanagement.application.service.dto.query.GetMascotaByIdQuery;
import com.jcaa.usersmanagement.domain.enums.MascotaGenero;
import com.jcaa.usersmanagement.domain.enums.MascotaTamano;
import com.jcaa.usersmanagement.domain.enums.MascotaTipo;
import com.jcaa.usersmanagement.domain.model.MascotaModel;
import com.jcaa.usersmanagement.domain.valueobject.MascotaId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests para MascotaApplicationMapper.
 *
 * <p>Cubre: fromCreateCommandToModel, fromUpdateCommandToModel,
 * fromGetByIdQueryToId y fromDeleteCommandToId.
 */
@DisplayName("MascotaApplicationMapper")
class MascotaApplicationMapperTest {

    private static final CreateMascotaCommand CREATE_COMMAND = new CreateMascotaCommand(
            "masc-001", "Firulais", "MACHO", 8.5, "MEDIANO", "Cafe",
            "Labrador", "Perro", "2020-03-15", "usr-001", "DOMESTICO", true, "Dr. Martinez");

    private static final UpdateMascotaCommand UPDATE_COMMAND = new UpdateMascotaCommand(
            "masc-001", "Firulais Updated", "HEMBRA", 9.0, "GRANDE", "Negro",
            "Golden", "Perro", "2020-03-15", "usr-002", "SALVAJE", false, "Dra. Lopez");

    // ── fromCreateCommandToModel ────────────────────────────────────────────────

    @Test
    @DisplayName("fromCreateCommandToModel() mapea todos los campos correctamente")
    void shouldMapCreateCommandToModel() {
        final MascotaModel model =
                MascotaApplicationMapper.fromCreateCommandToModel(CREATE_COMMAND);

        assertAll(
                "mapeo de CreateMascotaCommand a MascotaModel",
                () -> assertEquals("masc-001",        model.getId().value()),
                () -> assertEquals("Firulais",         model.getNombre().value()),
                () -> assertEquals(MascotaGenero.MACHO, model.getGenero()),
                () -> assertEquals(8.5,                model.getPeso().value()),
                () -> assertEquals(MascotaTamano.MEDIANO, model.getTamano()),
                () -> assertEquals("Cafe",             model.getColor().value()),
                () -> assertEquals("Labrador",         model.getRaza().value()),
                () -> assertEquals("Perro",            model.getEspecie().value()),
                () -> assertEquals("2020-03-15",       model.getFechaNacimiento().value()),
                () -> assertEquals("usr-001",          model.getPropietario().value()),
                () -> assertEquals(MascotaTipo.DOMESTICO, model.getTipo()),
                () -> assertTrue(model.getTieneVacunas().value()),
                () -> assertEquals("Dr. Martinez",     model.getVeterinario().value()));
    }

    // ── fromUpdateCommandToModel ────────────────────────────────────────────────

    @Test
    @DisplayName("fromUpdateCommandToModel() mapea todos los campos correctamente")
    void shouldMapUpdateCommandToModel() {
        final MascotaModel model =
                MascotaApplicationMapper.fromUpdateCommandToModel(UPDATE_COMMAND);

        assertAll(
                "mapeo de UpdateMascotaCommand a MascotaModel",
                () -> assertEquals("masc-001",          model.getId().value()),
                () -> assertEquals("Firulais Updated",   model.getNombre().value()),
                () -> assertEquals(MascotaGenero.HEMBRA, model.getGenero()),
                () -> assertEquals(9.0,                  model.getPeso().value()),
                () -> assertEquals(MascotaTamano.GRANDE,  model.getTamano()),
                () -> assertEquals(MascotaTipo.SALVAJE,   model.getTipo()),
                () -> assertFalse(model.getTieneVacunas().value()));
    }

    // ── fromGetByIdQueryToId ────────────────────────────────────────────────────

    @Test
    @DisplayName("fromGetByIdQueryToId() retorna MascotaId con el valor del query")
    void shouldMapQueryToId() {
        final GetMascotaByIdQuery query = new GetMascotaByIdQuery("masc-001");
        final MascotaId id = MascotaApplicationMapper.fromGetByIdQueryToId(query);

        assertEquals("masc-001", id.value());
    }

    // ── fromDeleteCommandToId ───────────────────────────────────────────────────

    @Test
    @DisplayName("fromDeleteCommandToId() retorna MascotaId con el valor del command")
    void shouldMapDeleteCommandToId() {
        final DeleteMascotaCommand command = new DeleteMascotaCommand("masc-001");
        final MascotaId id = MascotaApplicationMapper.fromDeleteCommandToId(command);

        assertEquals("masc-001", id.value());
    }
}
