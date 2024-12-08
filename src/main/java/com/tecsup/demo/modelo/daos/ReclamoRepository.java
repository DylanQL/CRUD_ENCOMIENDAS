package com.tecsup.demo.modelo.daos;

import com.tecsup.demo.modelo.entidades.Reclamo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReclamoRepository extends JpaRepository<Reclamo, Integer> {
}
