package com.sanaesencia.usuarios_service.service;

import com.sanaesencia.usuarios_service.dto.*;
import com.sanaesencia.usuarios_service.entity.Usuario;
import com.sanaesencia.usuarios_service.repository.UsuarioRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Locale;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    public List<UsuarioResponse> listarUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioResponse::desde)
                .toList();
    }

    @Transactional(readOnly = true)
    public UsuarioResponse obtenerUsuario(Long id) {
        Usuario usuario = buscarPorId(id);
        return UsuarioResponse.desde(usuario);
    }

    @Transactional
    public UsuarioResponse crearUsuario(CrearUsuarioRequest request) {

        String correo = request.correo()
                .trim()
                .toLowerCase(Locale.ROOT);

        if (usuarioRepository.existsByCorreoIgnoreCase(correo)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "El correo ya está registrado"
            );
        }

        Usuario usuario = new Usuario();

        usuario.setNombre(request.nombre().trim());
        usuario.setCorreo(correo);
        usuario.setPasswordHash(
                passwordEncoder.encode(request.password())
        );
        usuario.setRol(request.rol());
        usuario.setActivo(true);

        Usuario guardado = usuarioRepository.save(usuario);

        return UsuarioResponse.desde(guardado);
    }

    @Transactional
    public UsuarioResponse actualizarUsuario(
            Long id,
            ActualizarUsuarioRequest request) {

        Usuario usuario = buscarPorId(id);

        String correo = request.correo()
                .trim()
                .toLowerCase(Locale.ROOT);

        boolean correoOcupado =
                usuarioRepository.findByCorreoIgnoreCase(correo)
                        .filter(u -> !u.getId().equals(id))
                        .isPresent();

        if (correoOcupado) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "El correo ya pertenece a otro usuario"
            );
        }

        usuario.setNombre(request.nombre().trim());
        usuario.setCorreo(correo);
        usuario.setRol(request.rol());
        usuario.setActivo(request.activo());

        if (request.password() != null
                && !request.password().isBlank()) {

            usuario.setPasswordHash(
                    passwordEncoder.encode(request.password())
            );
        }

        Usuario actualizado = usuarioRepository.save(usuario);

        return UsuarioResponse.desde(actualizado);
    }

    @Transactional
    public void eliminarUsuario(Long id) {
        Usuario usuario = buscarPorId(id);
        usuarioRepository.delete(usuario);
    }

    private Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Usuario no encontrado"
                ));
    }
}
