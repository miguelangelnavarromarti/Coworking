package coworking.backend_private.Controlador;

import coworking.backend_private.Entidad.GestionOferta;
import coworking.backend_private.Servicio.Interficie.IGestionOfertasServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String guardar (@ModelAttribute GestionOferta gestionOferta, RedirectAttributes attribute){
        gestionOfertasServicio.guardar(gestionOferta);
        attribute.addFlashAttribute("success", "Se ha guardado la Oferta con éxito");
        return "redirect:/gestionOfertas";
    }

    @GetMapping("/modificar/{codigo}")
    public String editar(@PathVariable("codigo") Integer codigo, Model model){
        GestionOferta gestionOferta = gestionOfertasServicio.buscarPorCodigo(codigo);
        model.addAttribute("titulo", "Modificar Oferta");
        model.addAttribute("oferta", gestionOferta);
        return "gestionOfertas/crear";
    }

    @GetMapping("/eliminar/{codigo}")
    public String editar(@PathVariable("codigo") Integer codigo, RedirectAttributes attribute){
        gestionOfertasServicio.eliminar(codigo);
        attribute.addFlashAttribute("success", "Se ha eliminado la Oferta con éxito");
        return "redirect:/gestionOfertas";
    }

}















