package com.tecsup.demo.servicios;

import com.tecsup.demo.modelo.entidades.Reclamo;
import java.util.List;

public interface ReclamoService {
    List<Reclamo> listar();
    void grabar(Reclamo reclamo);
    Reclamo buscar(Integer id);
    void eliminar(Integer id);
}
