package coworking.backend_private.Controlador;


import coworking.backend_private.Entidad.Idioma;
import coworking.backend_private.Entidad.TipoEspacio;
import coworking.backend_private.Servicio.IdiomasServicioImpl;
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
}
