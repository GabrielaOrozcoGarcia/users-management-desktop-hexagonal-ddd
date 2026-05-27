package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.MascotaModel;

public interface SaveMascotaPort {
  MascotaModel save(MascotaModel mascota);
}
