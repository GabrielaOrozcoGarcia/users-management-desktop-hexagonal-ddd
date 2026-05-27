package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller;

import com.jcaa.usersmanagement.application.port.in.*;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateMascotaRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.MascotaResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UpdateMascotaRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.mapper.MascotaDesktopMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public final class MascotaController {

  private final CreateMascotaUseCase createMascotaUseCase;
  private final UpdateMascotaUseCase updateMascotaUseCase;
  private final DeleteMascotaUseCase deleteMascotaUseCase;
  private final GetMascotaByIdUseCase getMascotaByIdUseCase;
  private final GetAllMascotasUseCase getAllMascotasUseCase;

  public List<MascotaResponse> listAllMascotas() {
    final var mascotas = getAllMascotasUseCase.execute();
    return MascotaDesktopMapper.toResponseList(mascotas);
  }

  public MascotaResponse findMascotaById(final String id) {
    final var query = MascotaDesktopMapper.toGetByIdQuery(id);
    final var mascota = getMascotaByIdUseCase.execute(query);
    return MascotaDesktopMapper.toResponse(mascota);
  }

  public MascotaResponse createMascota(final CreateMascotaRequest request) {
    final var command = MascotaDesktopMapper.toCreateCommand(request);
    final var mascota = createMascotaUseCase.execute(command);
    return MascotaDesktopMapper.toResponse(mascota);
  }

  public MascotaResponse updateMascota(final UpdateMascotaRequest request) {
    final var command = MascotaDesktopMapper.toUpdateCommand(request);
    final var mascota = updateMascotaUseCase.execute(command);
    return MascotaDesktopMapper.toResponse(mascota);
  }

  public void deleteMascota(final String id) {
    final var command = MascotaDesktopMapper.toDeleteCommand(id);
    deleteMascotaUseCase.execute(command);
  }
}
