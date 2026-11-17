package com.sanaesencia.agenda_service.service;

import com.sanaesencia.agenda_service.entity.Cita;
import com.sanaesencia.agenda_service.repository.CitaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CitaService {

    private final CitaRepository repository;

    public CitaService(CitaRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Cita> listar() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Cita obtenerPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cita no encontrada"));
    }

    @Transactional
    public Cita agendar(Cita cita) {
        boolean ocupado = repository.existsByProfesionalIdAndFechaAndHoraAndEstadoNot(
                cita.getProfesionalId(),
                cita.getFecha(),
                cita.getHora(),
                "Cancelada"
        );

        if (ocupado) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "La fecha o la hora seleccionada ya no está disponible para este profesional."
            );
        }

        return repository.save(cita);
    }

    @Transactional
    public Cita actualizar(Long id, Cita datos) {
        Cita actual = obtenerPorId(id);

        if (datos.getEstado() != null) actual.setEstado(datos.getEstado());
        if (datos.getPago() != null) actual.setPago(datos.getPago());
        if (datos.getFecha() != null) actual.setFecha(datos.getFecha());
        if (datos.getHora() != null) actual.setHora(datos.getHora());

        return repository.save(actual);
    }

    @Transactional
    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cita no encontrada");
        }
        repository.deleteById(id);
    }
}
