package com.sanaesencia.agenda_service.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "citas")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario_id")
    private Long usuarioId;

    @Column(nullable = false, length = 120)
    private String paciente;

    @Column(nullable = false, length = 160)
    private String correo;

    @Column(name = "servicio_id", nullable = false)
    private Long servicioId;

    @Column(name = "profesional_id", nullable = false)
    private Long profesionalId;

    @Column(nullable = false, length = 20)
    private String fecha;

    @Column(nullable = false, length = 20)
    private String hora;

    @Column(length = 20)
    private String modalidad = "Presencial";

    @Column(columnDefinition = "TEXT")
    private String mensaje;

    @Column(length = 30)
    private String estado = "Pendiente";

    @Column(length = 30)
    private String pago = "No realizado";

    @Column(name = "creada_en", length = 50)
    private String creadaEn;

    public Cita() {
    }

    @PrePersist
    protected void alCrear() {
        if (creadaEn == null || creadaEn.isBlank()) {
            creadaEn = LocalDateTime.now().toString();
        }
        if (estado == null || estado.isBlank()) {
            estado = "Pendiente";
        }
        if (pago == null || pago.isBlank()) {
            pago = "No realizado";
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Long getServicioId() {
        return servicioId;
    }

    public void setServicioId(Long servicioId) {
        this.servicioId = servicioId;
    }

    public Long getProfesionalId() {
        return profesionalId;
    }

    public void setProfesionalId(Long profesionalId) {
        this.profesionalId = profesionalId;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPago() {
        return pago;
    }

    public void setPago(String pago) {
        this.pago = pago;
    }

    public String getCreadaEn() {
        return creadaEn;
    }

    public void setCreadaEn(String creadaEn) {
        this.creadaEn = creadaEn;
    }
}
