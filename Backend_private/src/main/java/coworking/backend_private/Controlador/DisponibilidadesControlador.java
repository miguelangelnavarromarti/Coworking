package coworking.backend_private.Controlador;

import coworking.backend_private.Entidad.Espacio;
import coworking.backend_private.Repositorio.EspaciosRepositorio;
import coworking.backend_private.Servicio.EspaciosServicioImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/disponibilidad")
public class DisponibilidadesControlador {

    @Autowired
    DisponibilidadesRestControlador disponibilidadesRestControlador;

    @Autowired
    private EspaciosServicioImpl espaciosServicio;

    @GetMapping("/{codigoEspacio}/{stringDia}")
    public String verDisponibilidad(@PathVariable("codigoEspacio") Integer codigoEspacio, @PathVariable("stringDia") String stringDia, Model model) {
        List<Espacio> espacioList = espaciosServicio.listaEspacio();
        model.addAttribute("espacios",espacioList);
        model.addAttribute("codigoEspacio", codigoEspacio);
        model.addAttribute("stringDia", stringDia);
        return "disponibilidad/ver";
    }
}
