-- =============================================
-- Script de creación de la base de datos
-- Gestión de Usuarios y Mascotas - Arquitectura Hexagonal
-- =============================================

CREATE DATABASE IF NOT EXISTS crud_usuarios_hex
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE crud_usuarios_hex;

-- ─── TABLA USUARIOS ──────────────────────────────────────────────────────────
CREATE TABLE IF NOT EXISTS users (
    id          VARCHAR(36)  NOT NULL PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    email       VARCHAR(150) NOT NULL UNIQUE,
    password    VARCHAR(255) NOT NULL,
    role        ENUM('ADMIN', 'MEMBER', 'REVIEWER')                  NOT NULL,
    status      ENUM('ACTIVE', 'INACTIVE', 'PENDING', 'BLOCKED')     NOT NULL DEFAULT 'PENDING',
    created_at  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO users (id, name, email, password, role, status)
VALUES (
    '00000000-0000-0000-0000-000000000001',
    'Administrador',
    'admin@example.com',
    '$2a$12$placeholderHashReplaceWithRealBCryptHash',
    'ADMIN',
    'ACTIVE'
) ON DUPLICATE KEY UPDATE id = id;

-- ─── TABLA MASCOTAS ───────────────────────────────────────────────────────────
CREATE TABLE IF NOT EXISTS mascotas (
    id               VARCHAR(36)                    NOT NULL PRIMARY KEY,
    nombre           VARCHAR(100)                   NOT NULL,
    genero           ENUM('MACHO','HEMBRA')          NOT NULL,
    peso             DECIMAL(6,2)                   NOT NULL,
    tamano           ENUM('PEQUENO','MEDIANO','GRANDE') NOT NULL,
    color            VARCHAR(50)                    NOT NULL,
    raza             VARCHAR(100)                   NOT NULL,
    especie          VARCHAR(50)                    NOT NULL,
    fecha_nacimiento VARCHAR(20)                    NOT NULL,
    propietario      VARCHAR(36)                    NOT NULL,
    tipo             ENUM('DOMESTICO','SALVAJE')     NOT NULL DEFAULT 'DOMESTICO',
    tiene_vacunas    TINYINT(1)                     NOT NULL DEFAULT 0,
    veterinario      VARCHAR(150)                   NOT NULL,
    created_at       DATETIME                       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at       DATETIME                       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_mascota_propietario
        FOREIGN KEY (propietario) REFERENCES users(id)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO mascotas (id, nombre, genero, peso, tamano, color, raza, especie,
                      fecha_nacimiento, propietario, tipo, tiene_vacunas, veterinario)
VALUES (
    '11111111-1111-1111-1111-111111111111',
    'Firulais',
    'MACHO',
    12.50,
    'MEDIANO',
    'Cafe',
    'Labrador',
    'Perro',
    '2020-03-15',
    '00000000-0000-0000-0000-000000000001',
    'DOMESTICO',
    1,
    'Dr. Garcia'
) ON DUPLICATE KEY UPDATE id = id;
