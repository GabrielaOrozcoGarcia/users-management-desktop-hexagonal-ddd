package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.CreateMascotaCommand;
import com.jcaa.usersmanagement.domain.model.MascotaModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface CreateMascotaUseCase {
  MascotaModel execute(@NotNull @Valid CreateMascotaCommand command);
}
