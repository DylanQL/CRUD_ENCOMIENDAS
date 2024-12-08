package com.tecsup.demo.servicios;

import com.tecsup.demo.modelo.daos.ReclamoRepository;
import com.tecsup.demo.modelo.entidades.Reclamo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReclamoServiceImpl implements ReclamoService {

    @Autowired
    private ReclamoRepository reclamoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Reclamo> listar() {
        return reclamoRepository.findAll();
    }

    @Override
    @Transactional
    public void grabar(Reclamo reclamo) {
        reclamoRepository.save(reclamo);
    }

    @Override
    @Transactional(readOnly = true)
    public Reclamo buscar(Integer id) {
        return reclamoRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void eliminar(Integer id) {
        reclamoRepository.deleteById(id);
    }
}
