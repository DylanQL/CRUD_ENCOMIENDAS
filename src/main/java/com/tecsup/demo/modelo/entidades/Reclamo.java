package com.tecsup.demo.modelo.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "reclamos")
public class Reclamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "encomienda_id")
    private Integer encomiendaId;

    @ManyToOne
    @JoinColumn(name = "motivo_id")
    private Motivo motivo;

    private String descripcion;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha;

    private String estado;

    // Default Constructor
    public Reclamo() {}

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEncomiendaId() {
        return encomiendaId;
    }

    public void setEncomiendaId(Integer encomiendaId) {
        this.encomiendaId = encomiendaId;
    }

    public Motivo getMotivo() {
        return motivo;
    }

    public void setMotivo(Motivo motivo) {
        this.motivo = motivo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    // toString() Method (Optional)
    @Override
    public String toString() {
        return "Reclamo{" +
                "id=" + id +
                ", encomiendaId=" + encomiendaId +
                ", motivo=" + motivo +
                ", descripcion='" + descripcion + '\'' +
                ", fecha=" + fecha +
                ", estado='" + estado + '\'' +
                '}';
    }
}
