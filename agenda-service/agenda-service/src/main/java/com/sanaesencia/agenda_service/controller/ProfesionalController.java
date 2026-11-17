// ProfesionalController.java
package com.sanaesencia.agenda_service.controller;

import com.sanaesencia.agenda_service.entity.Profesional;
import com.sanaesencia.agenda_service.service.ProfesionalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profesionales")
@Tag(name = "Profesionales", description = "CRUD de terapeutas y especialistas")
public class ProfesionalController {

    private final ProfesionalService service;

    public ProfesionalController(ProfesionalService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Listar profesionales")
    public ResponseEntity<List<Profesional>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @PostMapping
    @Operation(summary = "Crear profesional (Solo ADMIN)")
    public ResponseEntity<Profesional> crear(@RequestBody Profesional prof) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(prof));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar profesional (Solo ADMIN)")
    public ResponseEntity<Profesional> actualizar(@PathVariable Long id, @RequestBody Profesional datos) {
        return ResponseEntity.ok(service.actualizar(id, datos));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar profesional (Solo ADMIN)")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
