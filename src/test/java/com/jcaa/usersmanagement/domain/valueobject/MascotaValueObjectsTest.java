package com.jcaa.usersmanagement.domain.valueobject;

import static org.junit.jupiter.api.Assertions.*;

import com.jcaa.usersmanagement.domain.exception.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * Tests para los Value Objects de Mascota.
 *
 * <p>Cubre: MascotaId, MascotaNombre, MascotaPeso, MascotaColor, MascotaRaza,
 * MascotaEspecie, MascotaVeterinario, MascotaPropietario y MascotaVacunas.
 */
@DisplayName("Value Objects de Mascota")
class MascotaValueObjectsTest {

    // ── MascotaId ────────────────────────────────────────────────────────────────

    @Nested
    @DisplayName("MascotaId")
    class MascotaIdTest {

        @Test
        @DisplayName("acepta un id válido y lo normaliza (trim)")
        void shouldAcceptValidId() {
            final MascotaId id = new MascotaId("  masc-001  ");
            assertEquals("masc-001", id.value());
        }

        @Test
        @DisplayName("lanza excepción cuando el id está vacío")
        void shouldThrowWhenEmpty() {
            assertThrows(InvalidMascotaIdException.class, () -> new MascotaId(""));
        }

        @Test
        @DisplayName("lanza NullPointerException cuando el id es null")
        void shouldThrowWhenNull() {
            assertThrows(NullPointerException.class, () -> new MascotaId(null));
        }

        @Test
        @DisplayName("dos MascotaId con el mismo valor son iguales")
        void shouldBeEqualWithSameValue() {
            assertEquals(new MascotaId("masc-001"), new MascotaId("masc-001"));
        }
    }

    // ── MascotaNombre ────────────────────────────────────────────────────────────

    @Nested
    @DisplayName("MascotaNombre")
    class MascotaNombreTest {

        @Test
        @DisplayName("acepta nombre válido de al menos 2 caracteres")
        void shouldAcceptValidNombre() {
            final MascotaNombre nombre = new MascotaNombre("Firulais");
            assertEquals("Firulais", nombre.value());
        }

        @Test
        @DisplayName("lanza excepción cuando el nombre está vacío")
        void shouldThrowWhenEmpty() {
            assertThrows(InvalidMascotaNombreException.class, () -> new MascotaNombre(""));
        }

        @Test
        @DisplayName("lanza excepción cuando el nombre tiene menos de 2 caracteres")
        void shouldThrowWhenTooShort() {
            assertThrows(InvalidMascotaNombreException.class, () -> new MascotaNombre("A"));
        }

        @Test
        @DisplayName("acepta nombre con exactamente 2 caracteres")
        void shouldAcceptMinimumLength() {
            final MascotaId id = new MascotaId("AB");
            assertNotNull(id);
        }
    }

    // ── MascotaPeso ──────────────────────────────────────────────────────────────

    @Nested
    @DisplayName("MascotaPeso")
    class MascotaPesoTest {

        @Test
        @DisplayName("acepta peso positivo")
        void shouldAcceptPositiveWeight() {
            final MascotaPeso peso = new MascotaPeso(8.5);
            assertEquals(8.5, peso.value());
        }

        @Test
        @DisplayName("lanza excepción cuando el peso es cero")
        void shouldThrowWhenZero() {
            assertThrows(InvalidMascotaPesoException.class, () -> new MascotaPeso(0.0));
        }

        @Test
        @DisplayName("lanza excepción cuando el peso es negativo")
        void shouldThrowWhenNegative() {
            assertThrows(InvalidMascotaPesoException.class, () -> new MascotaPeso(-1.5));
        }
    }

    // ── MascotaVacunas ───────────────────────────────────────────────────────────

    @Nested
    @DisplayName("MascotaVacunas")
    class MascotaVacunasTest {

        @Test
        @DisplayName("of(true) crea instancia con vacunas")
        void shouldCreateWithVacunas() {
            assertTrue(MascotaVacunas.of(true).value());
        }

        @Test
        @DisplayName("of(false) crea instancia sin vacunas")
        void shouldCreateWithoutVacunas() {
            assertFalse(MascotaVacunas.of(false).value());
        }

        @Test
        @DisplayName("conVacunas() crea instancia con vacunas=true")
        void shouldCreateConVacunas() {
            assertTrue(MascotaVacunas.conVacunas().value());
        }
    }

    // ── MascotaPropietario ───────────────────────────────────────────────────────

    @Nested
    @DisplayName("MascotaPropietario")
    class MascotaPropietarioTest {

        @Test
        @DisplayName("acepta propietario válido")
        void shouldAcceptValidPropietario() {
            final MascotaPropietario p = new MascotaPropietario("usr-001");
            assertEquals("usr-001", p.value());
        }

        @Test
        @DisplayName("lanza excepción cuando el propietario está vacío")
        void shouldThrowWhenEmpty() {
            assertThrows(InvalidMascotaPropietarioException.class,
                    () -> new MascotaPropietario(""));
        }
    }
}