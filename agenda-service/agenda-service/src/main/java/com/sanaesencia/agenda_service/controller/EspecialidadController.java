// EspecialidadController.java
package com.sanaesencia.agenda_service.controller;

import com.sanaesencia.agenda_service.entity.Especialidad;
import com.sanaesencia.agenda_service.service.EspecialidadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/especialidades")
@Tag(name = "Especialidades", description = "CRUD de especialidades y servicios terapéuticos")
public class EspecialidadController {

    private final EspecialidadService service;

    public EspecialidadController(EspecialidadService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Listar especialidades")
    public ResponseEntity<List<Especialidad>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @PostMapping
    @Operation(summary = "Crear especialidad (Solo ADMIN)")
    public ResponseEntity<Especialidad> crear(@RequestBody Especialidad esp) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(esp));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar especialidad o marcar oferta (Solo ADMIN)")
    public ResponseEntity<Especialidad> actualizar(@PathVariable Long id, @RequestBody Especialidad datos) {
        return ResponseEntity.ok(service.actualizar(id, datos));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar especialidad (Solo ADMIN)")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
