package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.MascotaModel;
import java.util.Optional;

public interface GetMascotaByIdPort {
  Optional<MascotaModel> getById(String id);
}
