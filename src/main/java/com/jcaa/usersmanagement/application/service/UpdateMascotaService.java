package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.UpdateMascotaUseCase;
import com.jcaa.usersmanagement.application.port.out.GetMascotaByIdPort;
import com.jcaa.usersmanagement.application.port.out.UpdateMascotaPort;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateMascotaCommand;
import com.jcaa.usersmanagement.application.service.mapper.MascotaApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.MascotaNotFoundException;
import com.jcaa.usersmanagement.domain.model.MascotaModel;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public final class UpdateMascotaService implements UpdateMascotaUseCase {

  private final UpdateMascotaPort updateMascotaPort;
  private final GetMascotaByIdPort getMascotaByIdPort;
  private final Validator validator;

  @Override
  public MascotaModel execute(final UpdateMascotaCommand command) {
    validate(command);
    ensureExists(command.id());
    final MascotaModel mascota = MascotaApplicationMapper.fromUpdateCommandToModel(command);
    return updateMascotaPort.update(mascota);
  }

  private void validate(final UpdateMascotaCommand command) {
    final Set<ConstraintViolation<UpdateMascotaCommand>> v = validator.validate(command);
    if (!v.isEmpty()) throw new ConstraintViolationException(v);
  }

  private void ensureExists(final String id) {
    getMascotaByIdPort.getById(id)
        .orElseThrow(() -> MascotaNotFoundException.becauseIdWasNotFound(id));
  }
}
