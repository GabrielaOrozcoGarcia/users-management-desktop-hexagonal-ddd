package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.mapper;

import com.jcaa.usersmanagement.application.service.dto.command.CreateMascotaCommand;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteMascotaCommand;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateMascotaCommand;
import com.jcaa.usersmanagement.application.service.dto.query.GetMascotaByIdQuery;
import com.jcaa.usersmanagement.domain.model.MascotaModel;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateMascotaRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.MascotaResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UpdateMascotaRequest;

import java.util.List;

public final class MascotaDesktopMapper {

  private MascotaDesktopMapper() {}

  public static CreateMascotaCommand toCreateCommand(final CreateMascotaRequest req) {
    return new CreateMascotaCommand(
        req.id(), req.nombre(), req.genero(), req.peso(), req.tamano(),
        req.color(), req.raza(), req.especie(), req.fechaNacimiento(),
        req.propietario(), req.tipo(), req.tieneVacunas(), req.veterinario());
  }

  public static UpdateMascotaCommand toUpdateCommand(final UpdateMascotaRequest req) {
    return new UpdateMascotaCommand(
        req.id(), req.nombre(), req.genero(), req.peso(), req.tamano(),
        req.color(), req.raza(), req.especie(), req.fechaNacimiento(),
        req.propietario(), req.tipo(), req.tieneVacunas(), req.veterinario());
  }

  public static DeleteMascotaCommand toDeleteCommand(final String id) {
    return new DeleteMascotaCommand(id);
  }

  public static GetMascotaByIdQuery toGetByIdQuery(final String id) {
    return new GetMascotaByIdQuery(id);
  }

  /** Modelo → Response: extrae primitivos de cada VO/Enum */
  public static MascotaResponse toResponse(final MascotaModel m) {
    return new MascotaResponse(
        m.getId().value(),
        m.getNombre().value(),
        m.getGenero().name(),
        m.getPeso().value(),
        m.getTamano().name(),
        m.getColor().value(),
        m.getRaza().value(),
        m.getEspecie().value(),
        m.getFechaNacimiento().value(),
        m.getPropietario().value(),
        m.getTipo().name(),
        m.getTieneVacunas().value(),
        m.getVeterinario().value());
  }

  public static List<MascotaResponse> toResponseList(final List<MascotaModel> mascotas) {
    return mascotas.stream().map(MascotaDesktopMapper::toResponse).toList();
  }
}
