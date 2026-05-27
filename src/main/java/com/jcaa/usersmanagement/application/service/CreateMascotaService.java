package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.CreateMascotaUseCase;
import com.jcaa.usersmanagement.application.port.out.SaveMascotaPort;
import com.jcaa.usersmanagement.application.service.dto.command.CreateMascotaCommand;
import com.jcaa.usersmanagement.application.service.mapper.MascotaApplicationMapper;
import com.jcaa.usersmanagement.domain.model.MascotaModel;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import java.util.Set;

@Log
@RequiredArgsConstructor
public final class CreateMascotaService implements CreateMascotaUseCase {

  private final SaveMascotaPort saveMascotaPort;
  private final Validator validator;

  @Override
  public MascotaModel execute(final CreateMascotaCommand command) {
    validateCommand(command);
    final MascotaModel mascotaToSave = MascotaApplicationMapper.fromCreateCommandToModel(command);
    return saveMascotaPort.save(mascotaToSave);
  }

  private void validateCommand(final CreateMascotaCommand command) {
    final Set<ConstraintViolation<CreateMascotaCommand>> violations = validator.validate(command);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }
}
