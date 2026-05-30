package com.jcaa.usersmanagement.domain.model;

import static org.junit.jupiter.api.Assertions.*;

import com.jcaa.usersmanagement.domain.enums.MascotaGenero;
import com.jcaa.usersmanagement.domain.enums.MascotaTamano;
import com.jcaa.usersmanagement.domain.enums.MascotaTipo;
import com.jcaa.usersmanagement.domain.valueobject.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests para MascotaModel.
 *
 * <p>Cubre: creación, método vacunar() y equals/hashCode por value object.
 */
@DisplayName("MascotaModel")
class MascotaModelTest {

    private static MascotaModel mascota(final boolean vacunada) {
        return MascotaModel.create(
                new MascotaId("masc-001"),
                new MascotaNombre("Firulais"),
                MascotaGenero.MACHO,
                new MascotaPeso(8.5),
                MascotaTamano.MEDIANO,
                new MascotaColor("Cafe"),
                new MascotaRaza("Labrador"),
                new MascotaEspecie("Perro"),
                new MascotaFechaNacimiento("2020-03-15"),
                new MascotaPropietario("usr-001"),
                MascotaTipo.DOMESTICO,
                MascotaVacunas.of(vacunada),
                new MascotaVeterinario("Dr. Martinez"));
    }

    @Test
    @DisplayName("create() inicializa todos los campos correctamente")
    void shouldCreateWithAllFields() {
        final MascotaModel m = mascota(false);

        assertAll(
                "campos de MascotaModel",
                () -> assertEquals("masc-001",   m.getId().value()),
                () -> assertEquals("Firulais",   m.getNombre().value()),
                () -> assertEquals(MascotaGenero.MACHO,    m.getGenero()),
                () -> assertEquals(8.5,          m.getPeso().value()),
                () -> assertEquals(MascotaTamano.MEDIANO,  m.getTamano()),
                () -> assertEquals("Cafe",       m.getColor().value()),
                () -> assertEquals("Labrador",   m.getRaza().value()),
                () -> assertEquals("Perro",      m.getEspecie().value()),
                () -> assertEquals("2020-03-15", m.getFechaNacimiento().value()),
                () -> assertEquals("usr-001",    m.getPropietario().value()),
                () -> assertEquals(MascotaTipo.DOMESTICO,  m.getTipo()),
                () -> assertFalse(m.getTieneVacunas().value()),
                () -> assertEquals("Dr. Martinez", m.getVeterinario().value()));
    }

    @Test
    @DisplayName("vacunar() retorna nueva instancia con tieneVacunas=true")
    void shouldReturnVacunadaMascotaWithNewInstance() {
        final MascotaModel sinVacunas = mascota(false);
        final MascotaModel conVacunas = sinVacunas.vacunar();

        assertAll(
                "vacunar()",
                () -> assertFalse(sinVacunas.getTieneVacunas().value(), "original sin vacunas"),
                () -> assertTrue(conVacunas.getTieneVacunas().value(), "copia con vacunas"),
                () -> assertNotSame(sinVacunas, conVacunas, "debe ser instancia nueva"),
                () -> assertEquals(sinVacunas.getId(), conVacunas.getId(), "id no cambia"));
    }

    @Test
    @DisplayName("dos MascotaModel con los mismos valores son iguales")
    void shouldBeEqualWhenSameValues() {
        assertEquals(mascota(true), mascota(true));
    }
}
