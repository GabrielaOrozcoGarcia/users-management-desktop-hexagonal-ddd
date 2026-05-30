package com.jcaa.usersmanagement.application.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.jcaa.usersmanagement.application.port.out.GetAllMascotasPort;
import com.jcaa.usersmanagement.domain.enums.MascotaGenero;
import com.jcaa.usersmanagement.domain.enums.MascotaTamano;
import com.jcaa.usersmanagement.domain.enums.MascotaTipo;
import com.jcaa.usersmanagement.domain.model.MascotaModel;
import com.jcaa.usersmanagement.domain.valueobject.*;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Tests para GetAllMascotasService.
 *
 * <p>Cubre: lista con mascotas y lista vacía.
 */
@DisplayName("GetAllMascotasService")
@ExtendWith(MockitoExtension.class)
class GetAllMascotasServiceTest {

    @Mock private GetAllMascotasPort getAllMascotasPort;

    private GetAllMascotasService service;

    @BeforeEach
    void setUp() {
        service = new GetAllMascotasService(getAllMascotasPort);
    }

    // ── lista con mascotas ───────────────────────────────────────────────────────

    @Test
    @DisplayName("execute() retorna la lista de mascotas del puerto")
    void shouldReturnMascotasFromPort() {
        // Arrange
        final MascotaModel mascota = MascotaModel.create(
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

        when(getAllMascotasPort.getAll()).thenReturn(List.of(mascota));

        // Act
        final List<MascotaModel> result = service.execute();

        // Assert
        assertAll(
                "getAll con una mascota",
                () -> assertEquals(1, result.size(), "debe retornar exactamente una mascota"),
                () -> assertSame(mascota, result.get(0), "debe ser el mismo objeto del puerto"));
    }

    // ── lista vacía ──────────────────────────────────────────────────────────────

    @Test
    @DisplayName("execute() retorna lista vacía cuando no hay mascotas")
    void shouldReturnEmptyListWhenNoMascotas() {
        // Arrange
        when(getAllMascotasPort.getAll()).thenReturn(List.of());

        // Act
        final List<MascotaModel> result = service.execute();

        // Assert
        assertTrue(result.isEmpty(), "debe retornar lista vacía");
    }
}
