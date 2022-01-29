package coworking.backend_private.Controlador;

import coworking.backend_private.Entidad.GestionCancelacion;
import coworking.backend_private.Servicio.Interficie.IGestionCancelacionesServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/gestionCancelaciones")
public class GestionCancelacionesControlador {

    @Autowired
    private IGestionCancelacionesServicio gestionCancelacionesServicio;

    @GetMapping("")
    public String listarGestionCancelacion (Model model){
        List<GestionCancelacion> gestionCancelacionList = gestionCancelacionesServicio.verTodo();
        model.addAttribute("nombre","Gestión Cancelación");
        model.addAttribute("tabla", gestionCancelacionList);
        return "gestionCancelaciones/ver";
    }

    @GetMapping("/crear")
    public String crear (Model model){
        GestionCancelacion gestionCancelacion = new GestionCancelacion();
        model.addAttribute("titulo","Cancelación");
        model.addAttribute("cancelacion", gestionCancelacion);
        return "gestionCancelaciones/crear";
    }

    @PostMapping("/guardar")
    public String guardar (@ModelAttribute GestionCancelacion gestionCancelacion){
        gestionCancelacionesServicio.guardar(gestionCancelacion);
        return "redirect:/gestionCancelaciones";
    }

    @GetMapping("/editar/{codigo}")
    public String editar(@PathVariable("codigo") Integer codigo, Model model){
        GestionCancelacion gestionCancelacion = gestionCancelacionesServicio.buscarPorCodigo(codigo);
        model.addAttribute("titulo","Editar Oferta");
        model.addAttribute("cancelacion",gestionCancelacion);
        return "gestionCancelaciones/crear";
    }


}
