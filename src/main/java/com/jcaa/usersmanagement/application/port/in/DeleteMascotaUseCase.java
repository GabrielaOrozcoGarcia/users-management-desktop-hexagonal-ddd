package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.DeleteMascotaCommand;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface DeleteMascotaUseCase {
  void execute(@NotNull @Valid DeleteMascotaCommand command);
}
