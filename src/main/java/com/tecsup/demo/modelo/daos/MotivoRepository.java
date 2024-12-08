package com.tecsup.demo.modelo.daos;

import com.tecsup.demo.modelo.entidades.Motivo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MotivoRepository extends JpaRepository<Motivo, Integer> {
}
