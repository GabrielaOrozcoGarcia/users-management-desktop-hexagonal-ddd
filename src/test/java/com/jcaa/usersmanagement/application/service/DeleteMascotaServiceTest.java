package com.jcaa.usersmanagement.application.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.jcaa.usersmanagement.application.port.out.DeleteMascotaPort;
import com.jcaa.usersmanagement.application.port.out.GetMascotaByIdPort;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteMascotaCommand;
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
 * Tests para DeleteMascotaService.
 *
 * <p>Cubre: flujo feliz (delete invocado), mascota no encontrada y validación del command.
 */
@DisplayName("DeleteMascotaService")
@ExtendWith(MockitoExtension.class)
class DeleteMascotaServiceTest {

    @Mock private DeleteMascotaPort deleteMascotaPort;
    @Mock private GetMascotaByIdPort getMascotaByIdPort;

    private DeleteMascotaService service;

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
            service = new DeleteMascotaService(
                    deleteMascotaPort, getMascotaByIdPort, factory.getValidator());
        }
    }

    // ── flujo feliz ─────────────────────────────────────────────────────────────

    @Test
    @DisplayName("execute() invoca deleteMascotaPort cuando la mascota existe")
    void shouldDeleteWhenMascotaExists() {
        // Arrange
        final DeleteMascotaCommand command = new DeleteMascotaCommand("masc-001");
        when(getMascotaByIdPort.getById("masc-001")).thenReturn(Optional.of(MASCOTA_EXISTENTE));

        // Act
        service.execute(command);

        // Assert
        verify(deleteMascotaPort).delete("masc-001");
    }

    // ── mascota no encontrada ───────────────────────────────────────────────────

    @Test
    @DisplayName("execute() lanza MascotaNotFoundException cuando el id no existe")
    void shouldThrowWhenMascotaNotFound() {
        // Arrange
        final DeleteMascotaCommand command = new DeleteMascotaCommand("no-existe");
        when(getMascotaByIdPort.getById("no-existe")).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(MascotaNotFoundException.class, () -> service.execute(command));
        verify(deleteMascotaPort, never()).delete(any());
    }

    // ── validación del command ──────────────────────────────────────────────────

    @Test
    @DisplayName("execute() lanza ConstraintViolationException cuando el id está en blanco")
    void shouldThrowWhenCommandIsInvalid() {
        // Arrange
        final DeleteMascotaCommand command = new DeleteMascotaCommand("  ");

        // Act & Assert
        assertThrows(ConstraintViolationException.class, () -> service.execute(command));
        verifyNoInteractions(deleteMascotaPort, getMascotaByIdPort);
    }
}