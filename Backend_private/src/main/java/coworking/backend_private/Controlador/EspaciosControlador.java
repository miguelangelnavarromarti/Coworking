package coworking.backend_private.Controlador;

import coworking.backend_private.Entidad.Espacio;
import coworking.backend_private.Entidad.Idioma;
import coworking.backend_private.Entidad.TipoEspacio;
import coworking.backend_private.Entidad.TraduccionTipoEspacio;
import coworking.backend_private.Servicio.IEspaciosServicio;
import coworking.backend_private.Servicio.IIdiomasServicio;
import coworking.backend_private.Servicio.ITraduccionesTipoEspaciosServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/espacios")
public class EspaciosControlador {
    @Autowired
    private IEspaciosServicio espaciosServicio;

    //@Autowired
    //private ITraduccionesEspaciosServicio traduccionesEspaciosServicio;

    @Autowired
    private IIdiomasServicio idiomasServicio;

    @GetMapping("")
    public String getEspacios(Model model){
        List<Espacio> verEspacios = espaciosServicio.listaEspacio();

        model.addAttribute("nombre", "Espacios");
        model.addAttribute("espacios", verEspacios);

        return "espacios/ver";
    }

    /*@GetMapping("/crear")
    public String crearTipoEspacio(Model model) {

        TipoEspacio tipoEspacio = new TipoEspacio();

        model.addAttribute("nombre", "Tipo Espacio");
        model.addAttribute("tipoEspacio", tipoEspacio);

        return "tipoEspacios/crear";
    }

    @PostMapping("/guardar")
    public String guardarTipoEspacio(@ModelAttribute TipoEspacio tipoEspacio) {

        tipoEspaciosServicio.guardar(tipoEspacio);

        List<Idioma> idiomas = idiomasServicio.listaIdiomas();
        for (int i = 0; i < idiomas.size(); i++) {
            TraduccionTipoEspacio traduccionTipoEspacio = new TraduccionTipoEspacio(tipoEspacio,idiomas.get(i),"");
            traduccionesTipoEspaciosServicio.guardar(traduccionTipoEspacio);
        }

        return "redirect:/traduccionesTipoEspacios/" + tipoEspacio.getCodigo();
    }

    @PostMapping("/comprobacionCodigo")
    public String comprobacionCodigo(@ModelAttribute TipoEspacio tipoEspacio, Model model) {

        List<String> listaCodigos = tipoEspaciosServicio.listaCodigos();

        for (String codigo : listaCodigos) {
            if (Objects.equals(tipoEspacio.getCodigo(), codigo)) {
                model.addAttribute("errorCodigo", "Este codigo de Tipo Espacio '" + tipoEspacio.getCodigo() + "' ya existe.");
                return "redirect:/tipoEspacios/crear";
            }
        }

        return guardarTipoEspacio(tipoEspacio);
    }

    @GetMapping("/modificar/{codigo}")
    public String modificarTipoEspacio(@PathVariable("codigo") String codigo, Model model) {

        TipoEspacio tipoEspacio = tipoEspaciosServicio.verTipoEspacio(codigo);

        model.addAttribute("nombre", "Tipo Espacio");
        model.addAttribute("tipoEspacio", tipoEspacio);

        return "tipoEspacio/modificar";
    }*/
}
