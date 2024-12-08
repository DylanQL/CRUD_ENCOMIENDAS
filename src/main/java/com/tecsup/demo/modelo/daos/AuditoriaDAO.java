package com.tecsup.demo.modelo.daos;

import com.tecsup.demo.modelo.entidades.Auditoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditoriaDAO extends JpaRepository<Auditoria, Integer> {
}
