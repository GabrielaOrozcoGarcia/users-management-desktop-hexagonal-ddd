package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.GetAllMascotasUseCase;
import com.jcaa.usersmanagement.application.port.out.GetAllMascotasPort;
import com.jcaa.usersmanagement.domain.model.MascotaModel;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public final class GetAllMascotasService implements GetAllMascotasUseCase {

  private final GetAllMascotasPort getAllMascotasPort;

  @Override
  public List<MascotaModel> execute() {
    return getAllMascotasPort.getAll();
  }
}
