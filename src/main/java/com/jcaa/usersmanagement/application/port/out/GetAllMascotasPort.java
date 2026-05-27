package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.MascotaModel;
import java.util.List;

public interface GetAllMascotasPort {
  List<MascotaModel> getAll();
}
