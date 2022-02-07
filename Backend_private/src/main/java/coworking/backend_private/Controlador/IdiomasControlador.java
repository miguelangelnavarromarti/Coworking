package coworking.backend_private.Controlador;


import coworking.backend_private.Entidad.*;
import coworking.backend_private.Servicio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/idiomas")
public class IdiomasControlador {

    @Autowired
    private IdiomasServicioImpl idiomasServicio;

    @Autowired
    private EspaciosServicioImpl espaciosServicio;
    @Autowired
    private TipoEspaciosServicioImpl tipoEspaciosServicio;
    @Autowired
    private TraduccionesEspaciosServicioImpl traduccionesEspaciosServicio;
    @Autowired
    TraduccionesTipoEspaciosServicioImpl traduccionesTipoEspaciosServicio;

    @GetMapping("")
    public String getIdiomas(Model model){
        List<Idioma> verIdiomas = idiomasServicio.listaIdiomas();

        model.addAttribute("nombre", "Idiomas");
        model.addAttribute("idiomas", verIdiomas);

        return "idiomas/ver";
    }

    @GetMapping("/crear")
    public String crearIdiomas(Model model) {

        Idioma idioma = new Idioma();

        model.addAttribute("nombre", "Idioma");
        model.addAttribute("idioma", idioma);

        return "idiomas/crear";
    }

    @PostMapping("/guardar")
    public String guardarIdioma(@ModelAttribute Idioma idioma) {

        idiomasServicio.guardar(idioma);
        generarTraduccionesEspacio(idioma);
        generarTraduccionesTipoEspacio(idioma);
        System.out.println("Idioma guardado con éxito");

        return "redirect:/idiomas";
    }

    @GetMapping("/editar/{codigo}")
    public String editar(@PathVariable("codigo") String codigo, Model model){
        Idioma idioma = idiomasServicio.verIdioma(codigo);
        model.addAttribute("nombre","Idioma");
        model.addAttribute("idioma",idioma);
        return "idiomas/editar";
    }

    @PostMapping("/comprobacionCodigo")
    public String comprobacionCodigo(@ModelAttribute Idioma idioma, Model model) {

        List<String> listaCodigos = idiomasServicio.listaCodigos();

        for (String codigo : listaCodigos) {
            if (Objects.equals(idioma.getCodigo(), codigo)) {
                model.addAttribute("errorCodigo", "Este código de Idioma '" + idioma.getCodigo() + "' ya existe.");
                return "redirect:idiomas/ver";
            }
        }

        return guardarIdioma(idioma);
    }
    private void generarTraduccionesEspacio(Idioma idioma){
        List<Espacio> espacioList = espaciosServicio.listaEspacio();
        for (Espacio e : espacioList){
            TraduccionEspacio tre = new TraduccionEspacio(e, idioma, e.getNombre(), e.getDescripcion());
            traduccionesEspaciosServicio.guardar(tre);
        }
    }

    private void generarTraduccionesTipoEspacio(Idioma idioma){
        List<TipoEspacio> tipoEspacioList = tipoEspaciosServicio.listaTipoEspacio();
        for (TipoEspacio te : tipoEspacioList){
            TraduccionTipoEspacio tre = new TraduccionTipoEspacio(te,idioma,te.getNombre());
            traduccionesTipoEspaciosServicio.guardar(tre);
        }
    }
}
