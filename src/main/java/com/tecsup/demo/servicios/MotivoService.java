package com.tecsup.demo.servicios;

import com.tecsup.demo.modelo.entidades.Motivo;
import java.util.List;

public interface MotivoService {
    List<Motivo> listar();
    void grabar(Motivo motivo);
    Motivo buscar(Integer id);
    void eliminar(Integer id);
}
