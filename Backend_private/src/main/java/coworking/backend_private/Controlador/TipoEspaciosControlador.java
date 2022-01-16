package coworking.backend_private.Controlador;

import coworking.backend_private.Entidad.Cliente;
import coworking.backend_private.Entidad.TipoEspacio;
import coworking.backend_private.Servicio.ITipoEspaciosServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/tipoEspacios")
public class TipoEspaciosControlador {

    @Autowired
    private ITipoEspaciosServicio tipoEspaciosServicio;

    @GetMapping("")
    public String getTipoEspacios(Model model){
        List<TipoEspacio> verTipoEspacio = tipoEspaciosServicio.listaTipoEspacio();

        model.addAttribute("nombre", "TipoEspacios");
        model.addAttribute("tipoEspacios", verTipoEspacio);

        return "tipoEspacios/ver";
    }
}
