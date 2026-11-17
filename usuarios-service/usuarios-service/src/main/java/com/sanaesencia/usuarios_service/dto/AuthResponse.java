package com.sanaesencia.usuarios_service.dto;

import com.sanaesencia.usuarios_service.entity.Rol;

public record AuthResponse(
        String token,
        Long id,
        String nombre,
        String correo,
        Rol rol
) {}