package coworking.backend_private.Controlador;

import coworking.backend_private.Entidad.Opinion;
import coworking.backend_private.Servicio.Interficie.IOpinionesServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/opiniones")
public class OpinionesControlador {

    @Autowired
    private IOpinionesServicio opinionesServicio;

    @GetMapping("")
    public String listarOpiniones(Model model){
        List<Opinion> opinionList = opinionesServicio.verTodo();
        model.addAttribute("nombre","Opiniones");
        model.addAttribute("opiniones",opinionList);
        return "opiniones/ver";
    }

}
