// CitaRepository.java
package com.sanaesencia.agenda_service.repository;

import com.sanaesencia.agenda_service.entity.Cita;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitaRepository extends JpaRepository<Cita, Long> {
    boolean existsByProfesionalIdAndFechaAndHoraAndEstadoNot(
            Long profesionalId, String fecha, String hora, String estado);
}
