package coworking.backend_private.Controlador;

import coworking.backend_private.Entidad.Idioma;
import coworking.backend_private.Entidad.Espacio;
import coworking.backend_private.Entidad.TraduccionEspacio;
import coworking.backend_private.Servicio.IIdiomasServicio;
import coworking.backend_private.Servicio.IEspaciosServicio;
import coworking.backend_private.Servicio.ITraduccionesEspaciosServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/traduccionesEspacios")
public class TraduccionesEspaciosControlador {

    @Autowired
    private ITraduccionesEspaciosServicio traduccionesEspaciosServicio;

    @Autowired
    private IEspaciosServicio espaciosServicio;

    @Autowired
    private IIdiomasServicio idiomasServicio;

    @GetMapping("/{codigoEspacio}")
    public String getTraduccionesEspacios(@PathVariable("codigoEspacio") Integer codigoEspacio, Model model){
        List<TraduccionEspacio> verTraduccionesEspacios = traduccionesEspaciosServicio.verTraduccionesPorCodigoEspacio(codigoEspacio);


        model.addAttribute("nombre", "TraduccionesEspacios");

        Espacio espacio = espaciosServicio.verEspacio(codigoEspacio);
        model.addAttribute("espacio", espacio.getNombre());
        model.addAttribute("traduccionesEspacios", verTraduccionesEspacios);

        return "traduccionesEspacios/ver";
    }

    @GetMapping("/modificar/{codigoIdioma}/{codigoEspacio}")
    public String modificarTraduccionesEspacios(@PathVariable("codigoIdioma") String codigoIdioma, @PathVariable("codigoEspacio") Integer codigoEspacio, Model model){
        Espacio espacio = espaciosServicio.verEspacio(codigoEspacio);
        Idioma idioma = idiomasServicio.verIdioma(codigoIdioma);
        TraduccionEspacio verTraduccionEspacio = traduccionesEspaciosServicio.verPorIdiomaYEspacio(idioma, espacio);

        model.addAttribute("espacio", espacio.getNombre());
        model.addAttribute("traduccionEspacio", verTraduccionEspacio);

        return "traduccionesEspacios/modificar";
    }

    @PostMapping("/guardar")
    public String guardarTraduccionesEspacios(@ModelAttribute TraduccionEspacio traduccionEspacio) {

        traduccionesEspaciosServicio.guardar(traduccionEspacio);
        System.out.println("Cliente guardado con éxito");

        return "redirect:/traduccionesEspacios/" + traduccionEspacio.getCodigoEspacio().getCodigo();
    }

    /*
    @PostMapping("/guardarPorEspacio")
    public String guardarTraduccionesPorEspacio(@ModelAttribute TraduccionEspacio[] listaTraduccionEspacio) {

        for (int i = 0; i < listaTraduccionEspacio.length; i++) {
            traduccionesEspaciosServicio.guardar(listaTraduccionEspacio[i]);
        }

        System.out.println("Cliente guardado con éxito");

        return "redirect:/espacios";
    }*/
}
