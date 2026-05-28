package com.jcaa.usersmanagement.infrastructure.entrypoint.rest.controller;

import com.jcaa.usersmanagement.application.port.in.CreateMascotaUseCase;
import com.jcaa.usersmanagement.application.port.in.DeleteMascotaUseCase;
import com.jcaa.usersmanagement.application.port.in.GetAllMascotasUseCase;
import com.jcaa.usersmanagement.application.port.in.GetMascotaByIdUseCase;
import com.jcaa.usersmanagement.application.port.in.UpdateMascotaUseCase;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateMascotaRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.MascotaResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UpdateMascotaRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.mapper.MascotaDesktopMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mascotas")
@RequiredArgsConstructor
public class MascotaRestController {

    private final CreateMascotaUseCase createMascotaUseCase;
    private final UpdateMascotaUseCase updateMascotaUseCase;
    private final DeleteMascotaUseCase deleteMascotaUseCase;
    private final GetMascotaByIdUseCase getMascotaByIdUseCase;
    private final GetAllMascotasUseCase getAllMascotasUseCase;

    // GET /api/mascotas
    @GetMapping
    public ResponseEntity<List<MascotaResponse>> listAllMascotas() {
        final var mascotas = getAllMascotasUseCase.execute();
        return ResponseEntity.ok(MascotaDesktopMapper.toResponseList(mascotas));
    }

    // GET /api/mascotas/{id}
    @GetMapping("/{id}")
    public ResponseEntity<MascotaResponse> findMascotaById(@PathVariable final String id) {
        final var query   = MascotaDesktopMapper.toGetByIdQuery(id);
        final var mascota = getMascotaByIdUseCase.execute(query);
        return ResponseEntity.ok(MascotaDesktopMapper.toResponse(mascota));
    }

    // POST /api/mascotas
    @PostMapping
    public ResponseEntity<MascotaResponse> createMascota(
            @RequestBody final CreateMascotaRequest request) {
        final var command = MascotaDesktopMapper.toCreateCommand(request);
        final var mascota = createMascotaUseCase.execute(command);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(MascotaDesktopMapper.toResponse(mascota));
    }

    // PUT /api/mascotas
    @PutMapping
    public ResponseEntity<MascotaResponse> updateMascota(
            @RequestBody final UpdateMascotaRequest request) {
        final var command = MascotaDesktopMapper.toUpdateCommand(request);
        final var mascota = updateMascotaUseCase.execute(command);
        return ResponseEntity.ok(MascotaDesktopMapper.toResponse(mascota));
    }

    // DELETE /api/mascotas/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMascota(@PathVariable final String id) {
        final var command = MascotaDesktopMapper.toDeleteCommand(id);
        deleteMascotaUseCase.execute(command);
        return ResponseEntity.noContent().build();
    }
}
