package com.jcaa.usersmanagement.application.service.mapper;

import com.jcaa.usersmanagement.application.service.dto.command.CreateMascotaCommand;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteMascotaCommand;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateMascotaCommand;
import com.jcaa.usersmanagement.application.service.dto.query.GetMascotaByIdQuery;
import com.jcaa.usersmanagement.domain.enums.MascotaGenero;
import com.jcaa.usersmanagement.domain.enums.MascotaTamano;
import com.jcaa.usersmanagement.domain.enums.MascotaTipo;
import com.jcaa.usersmanagement.domain.model.MascotaModel;
import com.jcaa.usersmanagement.domain.valueobject.*;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MascotaApplicationMapper {

  public MascotaModel fromCreateCommandToModel(final CreateMascotaCommand command) {
    return MascotaModel.create(
        new MascotaId(command.id()),
        new MascotaNombre(command.nombre()),
        MascotaGenero.fromString(command.genero()),
        new MascotaPeso(command.peso()),
        MascotaTamano.fromString(command.tamano()),
        new MascotaColor(command.color()),
        new MascotaRaza(command.raza()),
        new MascotaEspecie(command.especie()),
        new MascotaFechaNacimiento(command.fechaNacimiento()),
        new MascotaPropietario(command.propietario()),
        MascotaTipo.fromString(command.tipo()),
        MascotaVacunas.of(command.tieneVacunas()),
        new MascotaVeterinario(command.veterinario()));
  }

  public MascotaModel fromUpdateCommandToModel(final UpdateMascotaCommand command) {
    return new MascotaModel(
        new MascotaId(command.id()),
        new MascotaNombre(command.nombre()),
        MascotaGenero.fromString(command.genero()),
        new MascotaPeso(command.peso()),
        MascotaTamano.fromString(command.tamano()),
        new MascotaColor(command.color()),
        new MascotaRaza(command.raza()),
        new MascotaEspecie(command.especie()),
        new MascotaFechaNacimiento(command.fechaNacimiento()),
        new MascotaPropietario(command.propietario()),
        MascotaTipo.fromString(command.tipo()),
        MascotaVacunas.of(command.tieneVacunas()),
        new MascotaVeterinario(command.veterinario()));
  }

  public MascotaId fromGetByIdQueryToId(final GetMascotaByIdQuery query) {
    return new MascotaId(query.id());
  }

  public MascotaId fromDeleteCommandToId(final DeleteMascotaCommand command) {
    return new MascotaId(command.id());
  }
}
