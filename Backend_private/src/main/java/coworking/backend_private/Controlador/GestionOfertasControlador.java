package coworking.backend_private.Controlador;

import coworking.backend_private.Entidad.GestionOferta;
import coworking.backend_private.Servicio.Interficie.IGestionOfertasServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/gestionOfertas")
public class GestionOfertasControlador {

    @Autowired
    private IGestionOfertasServicio gestionOfertasServicio;

    @GetMapping("")
    public String listarGestionOfertas (Model model){
        List<GestionOferta> gestionOfertaList = gestionOfertasServicio.verTodo();
        model.addAttribute("nombre", "Gestión Ofertas");
        model.addAttribute("tabla", gestionOfertaList);
        return "gestionOfertas/ver";
    }

    @GetMapping("/crear")
    public String crear(Model model){
        GestionOferta gestionOferta = new GestionOferta();
        model.addAttribute("titulo", "Crear Oferta");
        model.addAttribute("oferta",gestionOferta);
        return "gestionOfertas/crear";
    }

    @PostMapping("/guardar")
    public String guardar (@ModelAttribute GestionOferta gestionOferta){
        gestionOfertasServicio.guardar(gestionOferta);
        return "redirect:/gestionOfertas";
    }

    @GetMapping("/editar/{codigo}")
    public String editar(@PathVariable("codigo") Integer codigo, Model model){
        GestionOferta gestionOferta = gestionOfertasServicio.buscarPorCodigo(codigo);
        model.addAttribute("titulo", "Editar Oferta");
        model.addAttribute("oferta", gestionOferta);
        return "gestionOfertas/crear";
    }

    @GetMapping("/eliminar/{codigo}")
    public String editar(@PathVariable("codigo") Integer codigo){
        gestionOfertasServicio.eliminar(codigo);
        return "redirect:/gestionOfertas";
    }

}















