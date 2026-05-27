package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.GetMascotaByIdUseCase;
import com.jcaa.usersmanagement.application.port.out.GetMascotaByIdPort;
import com.jcaa.usersmanagement.application.service.dto.query.GetMascotaByIdQuery;
import com.jcaa.usersmanagement.application.service.mapper.MascotaApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.MascotaNotFoundException;
import com.jcaa.usersmanagement.domain.model.MascotaModel;
import com.jcaa.usersmanagement.domain.valueobject.MascotaId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public final class GetMascotaByIdService implements GetMascotaByIdUseCase {

  private final GetMascotaByIdPort getMascotaByIdPort;
  private final Validator validator;

  @Override
  public MascotaModel execute(final GetMascotaByIdQuery query) {
    validate(query);
    final MascotaId id = MascotaApplicationMapper.fromGetByIdQueryToId(query);
    return getMascotaByIdPort
        .getById(id.value())
        .orElseThrow(() -> MascotaNotFoundException.becauseIdWasNotFound(id.value()));
  }

  private void validate(final GetMascotaByIdQuery query) {
    final Set<ConstraintViolation<GetMascotaByIdQuery>> v = validator.validate(query);
    if (!v.isEmpty()) throw new ConstraintViolationException(v);
  }
}
