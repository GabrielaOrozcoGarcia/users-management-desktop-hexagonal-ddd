package com.jcaa.usersmanagement.domain.event;

import static org.junit.jupiter.api.Assertions.*;

import com.jcaa.usersmanagement.domain.enums.MascotaGenero;
import com.jcaa.usersmanagement.domain.enums.MascotaTamano;
import com.jcaa.usersmanagement.domain.enums.MascotaTipo;
import com.jcaa.usersmanagement.domain.model.MascotaModel;
import com.jcaa.usersmanagement.domain.valueobject.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests para los eventos de dominio de Mascota:
 * MascotaCreatedDomainEvent, MascotaUpdatedDomainEvent, MascotaDeletedDomainEvent.
 */
@DisplayName("Eventos de dominio de Mascota")
class MascotaDomainEventsTest {

    private static final MascotaModel MASCOTA = MascotaModel.create(
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
            MascotaVacunas.of(true),
            new MascotaVeterinario("Dr. Martinez"));

    @Test
    @DisplayName("MascotaCreatedDomainEvent almacena la mascota correctamente")
    void shouldCreateCreatedEvent() {
        final MascotaCreatedDomainEvent event = new MascotaCreatedDomainEvent(MASCOTA);

        assertAll(
                "MascotaCreatedDomainEvent",
                () -> assertNotNull(event),
                () -> assertSame(MASCOTA, event.getMascota(),
                        "debe guardar la misma instancia de mascota"));
    }

    @Test
    @DisplayName("MascotaUpdatedDomainEvent almacena la mascota correctamente")
    void shouldCreateUpdatedEvent() {
        final MascotaUpdatedDomainEvent event = new MascotaUpdatedDomainEvent(MASCOTA);

        assertAll(
                "MascotaUpdatedDomainEvent",
                () -> assertNotNull(event),
                () -> assertSame(MASCOTA, event.getMascota(),
                        "debe guardar la misma instancia de mascota"));
    }

    @Test
    @DisplayName("MascotaDeletedDomainEvent almacena el id correctamente")
    void shouldCreateDeletedEvent() {
        final MascotaDeletedDomainEvent event = new MascotaDeletedDomainEvent("masc-001");

        assertAll(
                "MascotaDeletedDomainEvent",
                () -> assertNotNull(event),
                () -> assertEquals("masc-001", event.getMascotaId(),
                        "debe guardar el id de la mascota eliminada"));
    }
}
