package com.tecsup.demo.controladores;

import com.tecsup.demo.modelo.entidades.Motivo;
import com.tecsup.demo.servicios.MotivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Map;

@Controller
@SessionAttributes("motivo")
public class MotivoController {

    @Autowired
    private MotivoService motivoService;

    @GetMapping("/listarMotivos")
    public String listar(Model model) {
        model.addAttribute("titulo", "Listado de Motivos");
        model.addAttribute("motivos", motivoService.listar());
        return "listarMotivosView";
    }

    @GetMapping("/formMotivo")
    public String crear(Map<String, Object> model) {
        Motivo motivo = new Motivo();
        model.put("motivo", motivo);
        model.put("titulo", "Formulario de Motivo");
        return "formMotivoView";
    }

    @GetMapping("/formMotivo/{id}")
    public String editar(@PathVariable(value = "id") Integer id, Map<String, Object> model) {
        Motivo motivo = null;
        if (id > 0) {
            motivo = motivoService.buscar(id);
            if (motivo == null) {
                return "redirect:/listarMotivos";
            }
        } else {
            return "redirect:/listarMotivos";
        }
        model.put("motivo", motivo);
        model.put("titulo", "Editar Motivo");
        return "formMotivoView";
    }

    @PostMapping("/guardarMotivo")
    public String guardar(@Valid Motivo motivo, BindingResult result, Model model, SessionStatus status) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de Motivo");
            return "formMotivoView";
        }
        motivoService.grabar(motivo);
        status.setComplete();
        return "redirect:/listarMotivos";
    }

    @GetMapping("/eliminarMotivo/{id}")
    public String eliminar(@PathVariable(value = "id") Integer id) {
        if (id > 0) {
            motivoService.eliminar(id);
        }
        return "redirect:/listarMotivos";
    }
}
