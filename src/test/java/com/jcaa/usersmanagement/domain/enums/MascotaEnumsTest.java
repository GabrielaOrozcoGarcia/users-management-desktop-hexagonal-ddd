package com.jcaa.usersmanagement.domain.enums;

import static org.junit.jupiter.api.Assertions.*;

import com.jcaa.usersmanagement.domain.exception.InvalidMascotaGeneroException;
import com.jcaa.usersmanagement.domain.exception.InvalidMascotaTamanoException;
import com.jcaa.usersmanagement.domain.exception.InvalidMascotaTipoException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * Tests para los enums de Mascota: MascotaGenero, MascotaTamano y MascotaTipo.
 */
@DisplayName("Enums de Mascota")
class MascotaEnumsTest {

    // ── MascotaGenero ────────────────────────────────────────────────────────────

    @Nested
    @DisplayName("MascotaGenero")
    class MascotaGeneroTest {

        @Test
        @DisplayName("fromString() retorna MACHO con valor en mayúsculas")
        void shouldReturnMachoFromUppercase() {
            assertEquals(MascotaGenero.MACHO, MascotaGenero.fromString("MACHO"));
        }

        @Test
        @DisplayName("fromString() retorna HEMBRA con valor en minúsculas")
        void shouldReturnHembraFromLowercase() {
            assertEquals(MascotaGenero.HEMBRA, MascotaGenero.fromString("hembra"));
        }

        @Test
        @DisplayName("fromString() lanza excepción con valor inválido")
        void shouldThrowForInvalidValue() {
            assertThrows(InvalidMascotaGeneroException.class,
                    () -> MascotaGenero.fromString("INVALIDO"));
        }

        @Test
        @DisplayName("valores del enum son exactamente MACHO y HEMBRA")
        void shouldHaveTwoValues() {
            assertEquals(2, MascotaGenero.values().length);
        }
    }

    // ── MascotaTamano ────────────────────────────────────────────────────────────

    @Nested
    @DisplayName("MascotaTamano")
    class MascotaTamanoTest {

        @Test
        @DisplayName("fromString() retorna PEQUENO correctamente")
        void shouldReturnPequeno() {
            assertEquals(MascotaTamano.PEQUENO, MascotaTamano.fromString("PEQUENO"));
        }

        @Test
        @DisplayName("fromString() retorna MEDIANO en minúsculas")
        void shouldReturnMedianoFromLowercase() {
            assertEquals(MascotaTamano.MEDIANO, MascotaTamano.fromString("mediano"));
        }

        @Test
        @DisplayName("fromString() retorna GRANDE correctamente")
        void shouldReturnGrande() {
            assertEquals(MascotaTamano.GRANDE, MascotaTamano.fromString("GRANDE"));
        }

        @Test
        @DisplayName("fromString() lanza excepción con valor inválido")
        void shouldThrowForInvalidValue() {
            assertThrows(InvalidMascotaTamanoException.class,
                    () -> MascotaTamano.fromString("GIGANTE"));
        }
    }

    // ── MascotaTipo ──────────────────────────────────────────────────────────────

    @Nested
    @DisplayName("MascotaTipo")
    class MascotaTipoTest {

        @Test
        @DisplayName("fromString() retorna DOMESTICO correctamente")
        void shouldReturnDomestico() {
            assertEquals(MascotaTipo.DOMESTICO, MascotaTipo.fromString("DOMESTICO"));
        }

        @Test
        @DisplayName("fromString() retorna SALVAJE en minúsculas")
        void shouldReturnSalvajeFromLowercase() {
            assertEquals(MascotaTipo.SALVAJE, MascotaTipo.fromString("salvaje"));
        }

        @Test
        @DisplayName("fromString() lanza excepción con valor inválido")
        void shouldThrowForInvalidValue() {
            assertThrows(InvalidMascotaTipoException.class,
                    () -> MascotaTipo.fromString("EXOTICO"));
        }

        @Test
        @DisplayName("fromBoolean(true) retorna DOMESTICO")
        void shouldReturnDomesticoFromTrue() {
            assertEquals(MascotaTipo.DOMESTICO, MascotaTipo.fromBoolean(true));
        }

        @Test
        @DisplayName("fromBoolean(false) retorna SALVAJE")
        void shouldReturnSalvajeFromFalse() {
            assertEquals(MascotaTipo.SALVAJE, MascotaTipo.fromBoolean(false));
        }

        @Test
        @DisplayName("toDomesticoBoolean() retorna true para DOMESTICO")
        void shouldReturnTrueForDomestico() {
            assertTrue(MascotaTipo.DOMESTICO.toDomesticoBoolean());
        }

        @Test
        @DisplayName("toDomesticoBoolean() retorna false para SALVAJE")
        void shouldReturnFalseForSalvaje() {
            assertFalse(MascotaTipo.SALVAJE.toDomesticoBoolean());
        }
    }
}
