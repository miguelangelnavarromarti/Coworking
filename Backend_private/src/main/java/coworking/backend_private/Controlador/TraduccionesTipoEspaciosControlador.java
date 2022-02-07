package coworking.backend_private.Controlador;

import coworking.backend_private.Entidad.Idioma;
import coworking.backend_private.Entidad.TipoEspacio;
import coworking.backend_private.Entidad.TraduccionTipoEspacio;
import coworking.backend_private.Servicio.Interficie.IIdiomasServicio;
import coworking.backend_private.Servicio.Interficie.ITipoEspaciosServicio;
import coworking.backend_private.Servicio.Interficie.ITraduccionesTipoEspaciosServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/traduccionesTipoEspacios")
public class TraduccionesTipoEspaciosControlador {

    @Autowired
    private ITraduccionesTipoEspaciosServicio traduccionesTipoEspaciosServicio;

    @Autowired
    private ITipoEspaciosServicio tipoEspaciosServicio;

    @Autowired
    private IIdiomasServicio idiomasServicio;

    @GetMapping("/{codigoTipoEspacio}")
    public String getTraduccionesTipoEspacios(@PathVariable("codigoTipoEspacio") String codigoTipoEspacio, Model model){
        List<TraduccionTipoEspacio> verTraduccionesTipoEspacios = traduccionesTipoEspaciosServicio.verTraduccionesPorCodigoTipoEspacio(codigoTipoEspacio);

        List<Idioma> idiomaList = idiomasServicio.listaIdiomas();
        model.addAttribute("listaIdiomas",idiomaList);

        model.addAttribute("nombre", "TraduccionesTipoEspacios");
        model.addAttribute("codigoTipoEspacio", codigoTipoEspacio);
        model.addAttribute("traduccionesTipoEspacios", verTraduccionesTipoEspacios);

        return "traduccionesTipoEspacios/ver";
    }

    @GetMapping("/modificar/{codigoIdioma}/{codigoTipoEspacio}")
    public String modificarTraduccionesTipoEspacios(@PathVariable("codigoIdioma") String codigoIdioma, @PathVariable("codigoTipoEspacio") String codigoTipoEspacio, Model model){
        TipoEspacio tipoEspacio = tipoEspaciosServicio.verTipoEspacio(codigoTipoEspacio);
        Idioma idioma = idiomasServicio.verIdioma(codigoIdioma);
        TraduccionTipoEspacio verTraduccionTipoEspacio = traduccionesTipoEspaciosServicio.verPorIdiomaYTipoEspacio(idioma, tipoEspacio);

        model.addAttribute("codigoTipoEspacio", codigoTipoEspacio);
        model.addAttribute("traduccionTipoEspacio", verTraduccionTipoEspacio);

        return "traduccionesTipoEspacios/modificar";
    }

    @PostMapping("/guardar")
    public String guardarTraduccionesTipoEspacios(@ModelAttribute TraduccionTipoEspacio traduccionTipoEspacio, RedirectAttributes attribute) {

        traduccionesTipoEspaciosServicio.guardar(traduccionTipoEspacio);

        attribute.addFlashAttribute("success", "Traducción guardada con éxito");
        return "redirect:/traduccionesTipoEspacios/" + traduccionTipoEspacio.getCodigoTipoEspacio().getCodigo();
    }
}
