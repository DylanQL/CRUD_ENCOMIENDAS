package com.tecsup.demo.aop;

import com.tecsup.demo.modelo.daos.AuditoriaDAO;
import com.tecsup.demo.modelo.entidades.Auditoria;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Calendar;

@Component
@Aspect
public class LogginAspecto {

    @Autowired
    private AuditoriaDAO auditoriaDao;

    @After("execution(* com.tecsup.demo.controladores.*Controller.guardar*(..)) ||" +
            "execution(* com.tecsup.demo.controladores.*Controller.editar*(..)) ||" +
            "execution(* com.tecsup.demo.controladores.*Controller.eliminar*(..))")
    public void auditoria(JoinPoint joinPoint) throws Throwable {
        Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        String metodo = joinPoint.getSignature().getName();
        Integer id = null;

        Object[] parametros = joinPoint.getArgs();
        String tabla = obtenerTablaParaEntidad(joinPoint);

        if (parametros.length > 0 && parametros[0] instanceof Integer) {
            id = (Integer) parametros[0];
        } else if (parametros.length > 0 && parametros[0] instanceof Object) {
            id = obtenerIdDesdeEntidad(parametros[0]);
        }

        auditoriaDao.save(new Auditoria(tabla, id, Calendar.getInstance().getTime(), "usuario", metodo));
        logger.info(metodo + "(): registrando auditoria para entidad.");
    }

    private String obtenerTablaParaEntidad(JoinPoint joinPoint) {
        String nombreClase = joinPoint.getTarget().getClass().getSimpleName();
        if (nombreClase.contains("Reclamo")) {
            return "reclamos";
        } else if (nombreClase.contains("Motivo")) {
            return "motivo";
        }
        return "desconocido";
    }

    private Integer obtenerIdDesdeEntidad(Object entidad) {
        try {
            return (Integer) entidad.getClass().getMethod("getId").invoke(entidad);
        } catch (Exception e) {
            return null;
        }
    }
}
