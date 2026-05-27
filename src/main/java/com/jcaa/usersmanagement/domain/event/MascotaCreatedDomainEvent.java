package com.jcaa.usersmanagement.domain.event;

import com.jcaa.usersmanagement.domain.model.MascotaModel;
import lombok.Getter;

import java.util.Map;

@Getter
public final class MascotaCreatedDomainEvent extends DomainEvent {

  private static final String EVENT_NAME = "mascota.created";

  private final MascotaModel mascota;

  public MascotaCreatedDomainEvent(final MascotaModel mascota) {
    super(EVENT_NAME);
    this.mascota = mascota;
  }

  @Override
  public Map<String, String> payload() {
    return Map.of(
        "id",          mascota.getId().value(),
        "nombre",      mascota.getNombre().value(),
        "especie",     mascota.getEspecie().value(),
        "propietario", mascota.getPropietario().value(),
        "tipo",        mascota.getTipo().name(),
        "vacunas",     String.valueOf(mascota.getTieneVacunas().value()));
  }
}
