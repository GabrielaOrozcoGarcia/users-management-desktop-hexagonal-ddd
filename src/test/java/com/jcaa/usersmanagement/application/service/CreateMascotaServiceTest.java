package com.jcaa.usersmanagement.application.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.jcaa.usersmanagement.application.port.out.SaveMascotaPort;
import com.jcaa.usersmanagement.application.service.dto.command.CreateMascotaCommand;
import com.jcaa.usersmanagement.domain.enums.MascotaGenero;
import com.jcaa.usersmanagement.domain.enums.MascotaTamano;
import com.jcaa.usersmanagement.domain.enums.MascotaTipo;
import com.jcaa.usersmanagement.domain.model.MascotaModel;
import com.jcaa.usersmanagement.domain.valueobject.*;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Tests para CreateMascotaService.
 *
 * <p>Cubre: flujo feliz (save invocado), validación del command con campos inválidos.
 */
@DisplayName("CreateMascotaService")
@ExtendWith(MockitoExtension.class)
class CreateMascotaServiceTest {

    @Mock private SaveMascotaPort saveMascotaPort;

    private CreateMascotaService service;

    private static final MascotaModel MASCOTA_GUARDADA = MascotaModel.create(
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
            service = new CreateMascotaService(saveMascotaPort, factory.getValidator());
        }
    }

    // ── flujo feliz ─────────────────────────────────────────────────────────────

    @Test
    @DisplayName("execute() guarda la mascota y retorna el modelo guardado")
    void shouldSaveMascotaAndReturnModel() {
        // Arrange
        final CreateMascotaCommand command = validCommand();
        when(saveMascotaPort.save(any())).thenReturn(MASCOTA_GUARDADA);

        // Act
        final MascotaModel result = service.execute(command);

        // Assert
        assertAll(
                "flujo feliz de CreateMascotaService",
                () -> assertNotNull(result, "resultado no debe ser null"),
                () -> assertEquals("masc-001", result.getId().value(), "id de la mascota guardada"));

        verify(saveMascotaPort).save(any(MascotaModel.class));
    }

    // ── validación del command ──────────────────────────────────────────────────

    @Test
    @DisplayName("execute() lanza ConstraintViolationException cuando el id está en blanco")
    void shouldThrowWhenIdIsBlank() {
        final CreateMascotaCommand command = new CreateMascotaCommand(
                "", "Firulais", "MACHO", 8.5, "MEDIANO", "Cafe",
                "Labrador", "Perro", "2020-03-15", "usr-001", "DOMESTICO", true, "Dr. Martinez");

        assertThrows(ConstraintViolationException.class, () -> service.execute(command));
        verifyNoInteractions(saveMascotaPort);
    }

    @Test
    @DisplayName("execute() lanza ConstraintViolationException cuando el peso es negativo")
    void shouldThrowWhenPesoIsNegative() {
        final CreateMascotaCommand command = new CreateMascotaCommand(
                "masc-001", "Firulais", "MACHO", -1.0, "MEDIANO", "Cafe",
                "Labrador", "Perro", "2020-03-15", "usr-001", "DOMESTICO", true, "Dr. Martinez");

        assertThrows(ConstraintViolationException.class, () -> service.execute(command));
        verifyNoInteractions(saveMascotaPort);
    }

    @Test
    @DisplayName("execute() lanza ConstraintViolationException cuando tieneVacunas es null")
    void shouldThrowWhenTieneVacunasIsNull() {
        final CreateMascotaCommand command = new CreateMascotaCommand(
                "masc-001", "Firulais", "MACHO", 8.5, "MEDIANO", "Cafe",
                "Labrador", "Perro", "2020-03-15", "usr-001", "DOMESTICO", null, "Dr. Martinez");

        assertThrows(ConstraintViolationException.class, () -> service.execute(command));
        verifyNoInteractions(saveMascotaPort);
    }

    // ── helper ──────────────────────────────────────────────────────────────────

    private CreateMascotaCommand validCommand() {
        return new CreateMascotaCommand(
                "masc-001", "Firulais", "MACHO", 8.5, "MEDIANO", "Cafe",
                "Labrador", "Perro", "2020-03-15", "usr-001", "DOMESTICO", true, "Dr. Martinez");
    }
}