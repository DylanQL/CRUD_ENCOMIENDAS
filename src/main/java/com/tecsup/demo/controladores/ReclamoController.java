package com.tecsup.demo.controladores;

import com.tecsup.demo.modelo.entidades.Reclamo;
import com.tecsup.demo.modelo.entidades.Motivo;
import com.tecsup.demo.servicios.ReclamoService;
import com.tecsup.demo.servicios.MotivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes("reclamo")
public class ReclamoController {

    @Autowired
    private ReclamoService reclamoService;

    @Autowired
    private MotivoService motivoService;

    @GetMapping("/listarReclamos")
    public String listar(Model model) {
        model.addAttribute("titulo", "Listado de Reclamos");
        model.addAttribute("reclamos", reclamoService.listar());
        return "listarReclamosView";
    }

    @GetMapping("/formReclamo")
    public String crear(Map<String, Object> model) {
        Reclamo reclamo = new Reclamo();
        List<Motivo> motivos = motivoService.listar();
        model.put("reclamo", reclamo);
        model.put("motivos", motivos);
        model.put("titulo", "Formulario de Reclamo");
        return "formReclamoView";
    }

    @GetMapping("/formReclamo/{id}")
    public String editar(@PathVariable(value = "id") Integer id, Map<String, Object> model) {
        Reclamo reclamo = null;
        if (id > 0) {
            reclamo = reclamoService.buscar(id);
            if (reclamo == null) {
                return "redirect:/listarReclamos";
            }
        } else {
            return "redirect:/listarReclamos";
        }
        List<Motivo> motivos = motivoService.listar();
        model.put("reclamo", reclamo);
        model.put("motivos", motivos);
        model.put("titulo", "Editar Reclamo");
        return "formReclamoView";
    }

    @PostMapping("/guardarReclamo")
    public String guardar(@Valid Reclamo reclamo, BindingResult result, Model model, SessionStatus status) {
        if (result.hasErrors()) {
            List<Motivo> motivos = motivoService.listar();
            model.addAttribute("motivos", motivos);
            model.addAttribute("titulo", "Formulario de Reclamo");
            return "formReclamoView";
        }

        // Retrieve the Motivo object based on the selected id
        Integer motivoId = reclamo.getMotivo().getId();
        Motivo motivo = motivoService.buscar(motivoId);
        reclamo.setMotivo(motivo);

        reclamoService.grabar(reclamo);
        status.setComplete();
        return "redirect:/listarReclamos";
    }



    @GetMapping("/eliminarReclamo/{id}")
    public String eliminar(@PathVariable(value = "id") Integer id) {
        if (id > 0) {
            reclamoService.eliminar(id);
        }
        return "redirect:/listarReclamos";
    }
}
