package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.domain.model.MascotaModel;
import java.util.List;

public interface GetAllMascotasUseCase {
  List<MascotaModel> execute();
}
