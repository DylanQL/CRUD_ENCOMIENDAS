package com.tecsup.demo.modelo.entidades;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "auditoria")
public class Auditoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String tabla;

    @Column(name = "id_registro")
    private Integer idRegistro;

    private Date fecha;

    private String usuario;

    private String tipo;

    // Constructors, Getters, Setters, toString()
    public Auditoria() {}

    public Auditoria(String tabla, Integer idRegistro, Date fecha, String usuario, String tipo) {
        this.tabla = tabla;
        this.idRegistro = idRegistro;
        this.fecha = fecha;
        this.usuario = usuario;
        this.tipo = tipo;
    }

    // Getters and Setters
    // ...
}
