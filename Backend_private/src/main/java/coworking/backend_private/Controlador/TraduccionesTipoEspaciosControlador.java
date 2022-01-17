package coworking.backend_private.Controlador;

import coworking.backend_private.Entidad.TipoEspacio;
import coworking.backend_private.Entidad.TraduccionTipoEspacio;
import coworking.backend_private.Servicio.ITraduccionesTipoEspaciosServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/traduccionesTipoEspacios")
public class TraduccionesTipoEspaciosControlador {

    @Autowired
    private ITraduccionesTipoEspaciosServicio traduccionesTipoEspaciosServicio;

    @GetMapping("/{codigoTipoEspacio}")
    public String getTraduccionesTipoEspacios(@PathVariable("codigoTipoEspacio") String codigoTipoEspacio, Model model){
        List<TraduccionTipoEspacio> verTraduccionesTipoEspacios = traduccionesTipoEspaciosServicio.verTraduccionesPorCodigoTipoEspacio(codigoTipoEspacio);

        model.addAttribute("nombre", "TraduccionesTipoEspacios");
        model.addAttribute("codigoTipoEspacio", codigoTipoEspacio);
        model.addAttribute("traduccionesTipoEspacios", verTraduccionesTipoEspacios);

        return "traduccionesTipoEspacios/ver";
    }
}
