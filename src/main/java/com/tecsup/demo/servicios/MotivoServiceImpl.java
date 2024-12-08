package com.tecsup.demo.servicios;

import com.tecsup.demo.modelo.daos.MotivoRepository;
import com.tecsup.demo.modelo.entidades.Motivo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MotivoServiceImpl implements MotivoService {

    @Autowired
    private MotivoRepository motivoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Motivo> listar() {
        return motivoRepository.findAll();
    }

    @Override
    @Transactional
    public void grabar(Motivo motivo) {
        motivoRepository.save(motivo);
    }

    @Override
    @Transactional(readOnly = true)
    public Motivo buscar(Integer id) {
        return motivoRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void eliminar(Integer id) {
        motivoRepository.deleteById(id);
    }
}
