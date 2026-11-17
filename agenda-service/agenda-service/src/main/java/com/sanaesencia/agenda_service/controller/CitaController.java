// CitaController.java
package com.sanaesencia.agenda_service.controller;

import com.sanaesencia.agenda_service.entity.Cita;
import com.sanaesencia.agenda_service.service.CitaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/citas")
@Tag(name = "Citas", description = "Gestión y reserva de horas médicas")
public class CitaController {

    private final CitaService service;

    public CitaController(CitaService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Listar todas las citas")
    public ResponseEntity<List<Cita>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener detalle de una cita por ID")
    public ResponseEntity<Cita> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @PostMapping
    @Operation(summary = "Agendar nueva cita", description = "Valida disponibilidad horaria del profesional")
    public ResponseEntity<Cita> crear(@RequestBody Cita cita) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.agendar(cita));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar estado o pago de una cita")
    public ResponseEntity<Cita> actualizar(@PathVariable Long id, @RequestBody Cita datos) {
        return ResponseEntity.ok(service.actualizar(id, datos));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar cita (Solo ADMIN)")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
