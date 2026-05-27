package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.DeleteMascotaUseCase;
import com.jcaa.usersmanagement.application.port.out.DeleteMascotaPort;
import com.jcaa.usersmanagement.application.port.out.GetMascotaByIdPort;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteMascotaCommand;
import com.jcaa.usersmanagement.application.service.mapper.MascotaApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.MascotaNotFoundException;
import com.jcaa.usersmanagement.domain.valueobject.MascotaId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public final class DeleteMascotaService implements DeleteMascotaUseCase {

  private final DeleteMascotaPort deleteMascotaPort;
  private final GetMascotaByIdPort getMascotaByIdPort;
  private final Validator validator;

  @Override
  public void execute(final DeleteMascotaCommand command) {
    validate(command);
    final MascotaId id = MascotaApplicationMapper.fromDeleteCommandToId(command);
    ensureExists(id.value());
    deleteMascotaPort.delete(id.value());
  }

  private void validate(final DeleteMascotaCommand command) {
    final Set<ConstraintViolation<DeleteMascotaCommand>> v = validator.validate(command);
    if (!v.isEmpty()) throw new ConstraintViolationException(v);
  }

  private void ensureExists(final String id) {
    getMascotaByIdPort.getById(id)
        .orElseThrow(() -> MascotaNotFoundException.becauseIdWasNotFound(id));
  }
}
