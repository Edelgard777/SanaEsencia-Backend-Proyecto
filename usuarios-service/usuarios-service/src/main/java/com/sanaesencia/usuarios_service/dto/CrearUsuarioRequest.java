package com.sanaesencia.usuarios_service.dto;

import com.sanaesencia.usuarios_service.entity.Rol;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CrearUsuarioRequest(

        @NotBlank
        @Size(max = 120)
        String nombre,

        @NotBlank
        @Email
        @Size(max = 160)
        String correo,

        @NotBlank
        @Size(min = 8, max = 100)
        String password,

        @NotNull
        Rol rol

) {}
