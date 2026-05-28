package com.jcaa.usersmanagement.infrastructure.entrypoint.rest.controller;

import com.jcaa.usersmanagement.application.port.in.CreateUserUseCase;
import com.jcaa.usersmanagement.application.port.in.DeleteUserUseCase;
import com.jcaa.usersmanagement.application.port.in.GetAllUsersUseCase;
import com.jcaa.usersmanagement.application.port.in.GetUserByIdUseCase;
import com.jcaa.usersmanagement.application.port.in.LoginUseCase;
import com.jcaa.usersmanagement.application.port.in.UpdateUserUseCase;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateUserRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.LoginRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UpdateUserRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UserResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.mapper.UserDesktopMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserRestController {

    private final CreateUserUseCase createUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final GetUserByIdUseCase getUserByIdUseCase;
    private final GetAllUsersUseCase getAllUsersUseCase;
    private final LoginUseCase loginUseCase;

    // GET /api/users
    @GetMapping
    public ResponseEntity<List<UserResponse>> listAllUsers() {
        final var users = getAllUsersUseCase.execute();
        return ResponseEntity.ok(UserDesktopMapper.toResponseList(users));
    }

    // GET /api/users/{id}
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findUserById(@PathVariable final String id) {
        final var query = UserDesktopMapper.toGetByIdQuery(id);
        final var user  = getUserByIdUseCase.execute(query);
        return ResponseEntity.ok(UserDesktopMapper.toResponse(user));
    }

    // POST /api/users
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody final CreateUserRequest request) {
        final var command = UserDesktopMapper.toCreateCommand(request);
        final var user    = createUserUseCase.execute(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(UserDesktopMapper.toResponse(user));
    }

    // PUT /api/users
    @PutMapping
    public ResponseEntity<UserResponse> updateUser(@RequestBody final UpdateUserRequest request) {
        final var command = UserDesktopMapper.toUpdateCommand(request);
        final var user    = updateUserUseCase.execute(command);
        return ResponseEntity.ok(UserDesktopMapper.toResponse(user));
    }

    // DELETE /api/users/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable final String id) {
        final var command = UserDesktopMapper.toDeleteCommand(id);
        deleteUserUseCase.execute(command);
        return ResponseEntity.noContent().build();
    }

    // POST /api/users/login
    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody final LoginRequest request) {
        final var command = UserDesktopMapper.toLoginCommand(request);
        final var user    = loginUseCase.execute(command);
        return ResponseEntity.ok(UserDesktopMapper.toResponse(user));
    }
}
