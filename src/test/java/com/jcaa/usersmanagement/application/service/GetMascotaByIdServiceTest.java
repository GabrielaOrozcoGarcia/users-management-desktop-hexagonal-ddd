package com.jcaa.usersmanagement.application.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.jcaa.usersmanagement.application.port.out.GetMascotaByIdPort;
import com.jcaa.usersmanagement.application.service.dto.query.GetMascotaByIdQuery;
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
 * Tests para GetMascotaByIdService.
 *
 * <p>Cubre: retorno de la mascota encontrada, MascotaNotFoundException y validación del query.
 */
@DisplayName("GetMascotaByIdService")
@ExtendWith(MockitoExtension.class)
class GetMascotaByIdServiceTest {

    @Mock private GetMascotaByIdPort getMascotaByIdPort;

    private GetMascotaByIdService service;

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

    @BeforeEach
    void setUp() {
        try (final ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            service = new GetMascotaByIdService(getMascotaByIdPort, factory.getValidator());
        }
    }

    // ── flujo feliz ─────────────────────────────────────────────────────────────

    @Test
    @DisplayName("execute() retorna la mascota cuando el id existe")
    void shouldReturnMascotaWhenFound() {
        // Arrange
        final GetMascotaByIdQuery query = new GetMascotaByIdQuery("masc-001");
        when(getMascotaByIdPort.getById("masc-001")).thenReturn(Optional.of(MASCOTA));

        // Act
        final MascotaModel result = service.execute(query);

        // Assert
        assertSame(MASCOTA, result, "debe retornar exactamente la mascota del puerto");
    }

    // ── mascota no encontrada ───────────────────────────────────────────────────

    @Test
    @DisplayName("execute() lanza MascotaNotFoundException cuando el id no existe")
    void shouldThrowWhenMascotaNotFound() {
        // Arrange
        final GetMascotaByIdQuery query = new GetMascotaByIdQuery("no-existe");
        when(getMascotaByIdPort.getById("no-existe")).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(MascotaNotFoundException.class, () -> service.execute(query));
    }

    // ── validación del query ────────────────────────────────────────────────────

    @Test
    @DisplayName("execute() lanza ConstraintViolationException cuando el id está en blanco")
    void shouldThrowWhenQueryIsInvalid() {
        // Arrange
        final GetMascotaByIdQuery query = new GetMascotaByIdQuery("");

        // Act & Assert
        assertThrows(ConstraintViolationException.class, () -> service.execute(query));
        verifyNoInteractions(getMascotaByIdPort);
    }
}