package com.sanaesencia.agenda_service.service;

import com.sanaesencia.agenda_service.entity.Profesional;
import com.sanaesencia.agenda_service.repository.ProfesionalRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProfesionalService {

    private final ProfesionalRepository repository;

    public ProfesionalService(ProfesionalRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Profesional> listar() {
        return repository.findAll();
    }

    @Transactional
    public Profesional crear(Profesional profesional) {
        return repository.save(profesional);
    }

    @Transactional
    public Profesional actualizar(Long id, Profesional datos) {
        Profesional actual = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Profesional no encontrado"));

        if (datos.getNombre() != null) actual.setNombre(datos.getNombre());
        if (datos.getEspecialidadId() != null) actual.setEspecialidadId(datos.getEspecialidadId());
        if (datos.getModalidad() != null) actual.setModalidad(datos.getModalidad());
        if (datos.getActivo() != null) actual.setActivo(datos.getActivo());

        return repository.save(actual);
    }

    @Transactional
    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Profesional no encontrado");
        }
        repository.deleteById(id);
    }
}
