package com.sanaesencia.usuarios_service.dto;

import com.sanaesencia.usuarios_service.entity.Rol;
import com.sanaesencia.usuarios_service.entity.Usuario;

import java.time.LocalDateTime;

public record UsuarioResponse(
        Long id,
        String nombre,
        String correo,
        Rol rol,
        boolean activo,
        LocalDateTime fechaCreacion
) {

    public static UsuarioResponse desde(Usuario usuario) {
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getCorreo(),
                usuario.getRol(),
                usuario.isActivo(),
                usuario.getFechaCreacion()
        );
    }
}
