package coworking.backend_private.Controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/disponibilidad")
public class DisponibilidadesControlador {

    @Autowired
    DisponibilidadesRestControlador disponibilidadesRestControlador;

    @GetMapping("/{codigoEspacio}/{stringDia}")
    public String verDisponibilidad(@PathVariable("codigoEspacio") Integer codigoEspacio, @PathVariable("stringDia") String stringDia, Model model) {
        model.addAttribute("codigoEspacio", codigoEspacio);
        model.addAttribute("stringDia", stringDia);
        return "disponibilidad/ver";
    }
}
