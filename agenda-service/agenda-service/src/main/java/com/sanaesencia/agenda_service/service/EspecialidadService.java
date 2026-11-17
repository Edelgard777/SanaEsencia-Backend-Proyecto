package com.sanaesencia.agenda_service.service;

import com.sanaesencia.agenda_service.entity.Especialidad;
import com.sanaesencia.agenda_service.repository.EspecialidadRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EspecialidadService {

    private final EspecialidadRepository repository;

    public EspecialidadService(EspecialidadRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Especialidad> listar() {
        return repository.findAll();
    }

    @Transactional
    public Especialidad crear(Especialidad especialidad) {
        return repository.save(especialidad);
    }

    @Transactional
    public Especialidad actualizar(Long id, Especialidad datos) {
        Especialidad actual = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Especialidad no encontrada"));

        if (datos.getNombre() != null) actual.setNombre(datos.getNombre());
        if (datos.getDescripcion() != null) actual.setDescripcion(datos.getDescripcion());
        if (datos.getModalidad() != null) actual.setModalidad(datos.getModalidad());
        if (datos.getPrecio() != null) actual.setPrecio(datos.getPrecio());
        if (datos.getActivo() != null) actual.setActivo(datos.getActivo());
        if (datos.getOferta() != null) actual.setOferta(datos.getOferta());
        if (datos.getEmoji() != null) actual.setEmoji(datos.getEmoji());

        return repository.save(actual);
    }

    @Transactional
    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Especialidad no encontrada");
        }
        repository.deleteById(id);
    }
}
