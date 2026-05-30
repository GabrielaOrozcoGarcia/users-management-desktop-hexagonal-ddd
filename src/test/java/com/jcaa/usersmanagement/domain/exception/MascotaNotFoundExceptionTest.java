package com.jcaa.usersmanagement.domain.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests para MascotaNotFoundException.
 */
@DisplayName("MascotaNotFoundException")
class MascotaNotFoundExceptionTest {

    @Test
    @DisplayName("becauseIdWasNotFound() incluye el id en el mensaje")
    void shouldIncludeIdInMessage() {
        final MascotaNotFoundException ex =
                MascotaNotFoundException.becauseIdWasNotFound("masc-001");

        assertAll(
                "MascotaNotFoundException",
                () -> assertNotNull(ex),
                () -> assertTrue(ex.getMessage().contains("masc-001"),
                        "el mensaje debe contener el id"));
    }
}
