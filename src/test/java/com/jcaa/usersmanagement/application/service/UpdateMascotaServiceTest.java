package com.jcaa.usersmanagement.application.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.jcaa.usersmanagement.application.port.out.GetMascotaByIdPort;
import com.jcaa.usersmanagement.application.port.out.UpdateMascotaPort;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateMascotaCommand;
import com.jcaa.usersmanagement.domain.enums.MascotaGenero;
import com.jcaa.usersmanagement.domain.enums.MascotaTamano;
import com.jcaa.usersmanagement.domain.enums.MascotaTipo;
import com.jcaa.usersmanagement.domain.exception.MascotaNotFoundException;
import com.jcaa.usersmanagement.domain.model.MascotaModel;
import com.jcaa.usersmanagement.domain.valueobject.*;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Tests para UpdateMascotaService.
 *
 * <p>Cubre: flujo feliz, mascota no encontrada y validación del command.
 */
@DisplayName("UpdateMascotaService")
@ExtendWith(MockitoExtension.class)
class UpdateMascotaServiceTest {

    @Mock private UpdateMascotaPort updateMascotaPort;
    @Mock private GetMascotaByIdPort getMascotaByIdPort;

    private UpdateMascotaService service;

    private static final MascotaModel MASCOTA_EXISTENTE = MascotaModel.create(
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

    @BeforeEach
    void setUp() {
        try (final ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            service = new UpdateMascotaService(
                    updateMascotaPort, getMascotaByIdPort, factory.getValidator());
        }
    }

    // ── flujo feliz ─────────────────────────────────────────────────────────────

    @Test
    @DisplayName("execute() actualiza la mascota cuando existe y los datos son válidos")
    void shouldUpdateMascotaWhenExists() {
        // Arrange
        final UpdateMascotaCommand command = validCommand();
        when(getMascotaByIdPort.getById("masc-001")).thenReturn(Optional.of(MASCOTA_EXISTENTE));
        when(updateMascotaPort.update(any())).thenReturn(MASCOTA_EXISTENTE);

        // Act
        final MascotaModel result = service.execute(command);

        // Assert
        assertNotNull(result, "resultado no debe ser null");
        verify(updateMascotaPort).update(any(MascotaModel.class));
    }

    // ── mascota no encontrada ───────────────────────────────────────────────────

    @Test
    @DisplayName("execute() lanza MascotaNotFoundException cuando el id no existe")
    void shouldThrowWhenMascotaNotFound() {
        // Arrange
        final UpdateMascotaCommand command = validCommand();
        when(getMascotaByIdPort.getById("masc-001")).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(MascotaNotFoundException.class, () -> service.execute(command));
        verify(updateMascotaPort, never()).update(any());
    }

    // ── validación del command ──────────────────────────────────────────────────

    @Test
    @DisplayName("execute() lanza ConstraintViolationException cuando el id está en blanco")
    void shouldThrowWhenCommandIsInvalid() {
        // Arrange — id en blanco y peso negativo
        final UpdateMascotaCommand command = new UpdateMascotaCommand(
                "", "Firulais", "MACHO", -1.0, "MEDIANO", "Cafe",
                "Labrador", "Perro", "2020-03-15", "usr-001", "DOMESTICO", true, "Dr. Martinez");

        // Act & Assert
        assertThrows(ConstraintViolationException.class, () -> service.execute(command));
        verifyNoInteractions(updateMascotaPort, getMascotaByIdPort);
    }

    // ── helper ──────────────────────────────────────────────────────────────────

    private UpdateMascotaCommand validCommand() {
        return new UpdateMascotaCommand(
                "masc-001", "Firulais Actualizado", "MACHO", 9.0, "GRANDE", "Cafe oscuro",
                "Labrador", "Perro", "2020-03-15", "usr-001", "DOMESTICO", true, "Dr. Perez");
    }
}
