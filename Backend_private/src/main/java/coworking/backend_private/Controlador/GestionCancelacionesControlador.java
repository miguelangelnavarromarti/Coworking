package coworking.backend_private.Controlador;

import coworking.backend_private.Entidad.GestionCancelacion;
import coworking.backend_private.Servicio.Interficie.IGestionCancelacionesServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        model.addAttribute("titulo","Nueva Cancelación");
        model.addAttribute("cancelacion", gestionCancelacion);
        return "gestionCancelaciones/crear";
    }

    @PostMapping("/guardar")
    public String guardar (@ModelAttribute GestionCancelacion gestionCancelacion, RedirectAttributes attribute){
        gestionCancelacionesServicio.guardar(gestionCancelacion);
        attribute.addFlashAttribute("success", "Se ha guardado la Cancelación con éxito");
        return "redirect:/gestionCancelaciones";
    }

    @GetMapping("/modificar/{codigo}")
    public String editar(@PathVariable("codigo") Integer codigo, Model model){
        GestionCancelacion gestionCancelacion = gestionCancelacionesServicio.buscarPorCodigo(codigo);
        model.addAttribute("titulo","Modificar Oferta");
        model.addAttribute("cancelacion",gestionCancelacion);
        return "gestionCancelaciones/crear";
    }


}
