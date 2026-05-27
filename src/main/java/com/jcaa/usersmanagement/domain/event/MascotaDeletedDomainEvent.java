package com.jcaa.usersmanagement.domain.event;

import lombok.Getter;

import java.util.Map;

@Getter
public final class MascotaDeletedDomainEvent extends DomainEvent {

  private static final String EVENT_NAME = "mascota.deleted";

  private final String mascotaId;

  public MascotaDeletedDomainEvent(final String mascotaId) {
    super(EVENT_NAME);
    this.mascotaId = mascotaId;
  }

  @Override
  public Map<String, String> payload() {
    return Map.of("id", mascotaId);
  }
}
