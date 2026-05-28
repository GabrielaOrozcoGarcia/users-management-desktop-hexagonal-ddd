package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.GetAllMascotasUseCase;
import com.jcaa.usersmanagement.application.port.out.GetAllMascotasPort;
import com.jcaa.usersmanagement.domain.model.MascotaModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllMascotasService implements GetAllMascotasUseCase {

  private final GetAllMascotasPort getAllMascotasPort;

  @Override
  public List<MascotaModel> execute() {
    return getAllMascotasPort.getAll();
  }
}
