package coworking.backend_private.Controlador;

import coworking.backend_private.Entidad.*;
import coworking.backend_private.Servicio.Interficie.IEspaciosServicio;
import coworking.backend_private.Servicio.Interficie.IIdiomasServicio;
import coworking.backend_private.Servicio.Interficie.ITipoEspaciosServicio;
import coworking.backend_private.Servicio.Interficie.ITraduccionesEspaciosServicio;
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

    @Autowired
    private ITipoEspaciosServicio tipoEspacioServicio;

    @Autowired
    private ITraduccionesEspaciosServicio traduccionesEspaciosServicio;

    @Autowired
    private IIdiomasServicio idiomasServicio;

    @GetMapping("")
    public String getEspacios(Model model){
        List<Espacio> verEspacios = espaciosServicio.listaEspacio();

        model.addAttribute("nombre", "Espacios");
        model.addAttribute("espacios", verEspacios);

        return "espacios/ver";
    }

    @GetMapping("{codigo}")
    public String getEspacioPorTipoEspacio(Model model, @PathVariable("codigo") String codigo){
        TipoEspacio tipoEspacio = tipoEspacioServicio.verTipoEspacio(codigo);

        List<Espacio> verEspacios = espaciosServicio.verEspaciosPorTipoEspacio(tipoEspacio);

        model.addAttribute("nombre", "Espacios");
        model.addAttribute("espacios", verEspacios);

        return "espacios/ver";
    }

    @GetMapping("/crear")
    public String crearEspacio(Model model) {

        Espacio espacio = new Espacio();

        model.addAttribute("nombre", "Espacio");
        model.addAttribute("espacio", espacio);

        List<TipoEspacio> listaTipoEspacio = tipoEspacioServicio.listaTipoEspacio();
        model.addAttribute("listaTipoEspacio",listaTipoEspacio);

        return "espacios/crear";
    }


    @PostMapping("/guardar")
    public String guardarEspacio(@ModelAttribute Espacio espacio) {
        List<Integer> lista = espaciosServicio.listaCodigos();

        for (Integer codigo : lista) {
            if(Objects.equals(codigo, espacio.getCodigo())) {
                espaciosServicio.guardar(espacio);
                return "redirect:/espacios";
            }
        }

        Integer codigo = espacio.getCodigo();
        espaciosServicio.guardar(espacio);

        List<Idioma> idiomas = idiomasServicio.listaIdiomas();
        for (Idioma idioma : idiomas) {
            TraduccionEspacio traduccionEspacio = new TraduccionEspacio(espacio, idioma, "", "");
            traduccionesEspaciosServicio.guardar(traduccionEspacio);
        }

        return "redirect:/traduccionesEspacios/" + espacio.getCodigo();
    }

    @GetMapping("/modificar/{codigo}")
    public String modificarEspacio(@PathVariable("codigo") Integer codigo, Model model) {

        Espacio espacio = espaciosServicio.verEspacio(codigo);

        model.addAttribute("nombre", "Espacio");
        model.addAttribute("nombreEspacio", espacio.getNombre());
        model.addAttribute("espacio", espacio);

        List<TipoEspacio> listaTipoEspacio = tipoEspacioServicio.listaTipoEspacio();
        model.addAttribute("listaTipoEspacio",listaTipoEspacio);

        return "espacios/modificar";
    }
}
