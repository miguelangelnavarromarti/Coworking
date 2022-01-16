package coworking.backend_private.Controlador;

import coworking.backend_private.Entidad.Cliente;
import coworking.backend_private.Entidad.TipoEspacio;
import coworking.backend_private.Servicio.ITipoEspaciosServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Objects;

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

    @GetMapping("/crear")
    public String crearTipoEspacio(Model model) {

        TipoEspacio tipoEspacio = new TipoEspacio();

        model.addAttribute("nombre", "Cliente");
        model.addAttribute("tipoEspacio", tipoEspacio);

        return "tipoEspacios/crear";
    }

    @PostMapping("/guardar")
    public String guardarTipoEspacio(@ModelAttribute TipoEspacio tipoEspacio) {

        tipoEspaciosServicio.guardar(tipoEspacio);
        System.out.println("Cliente guardado con éxito");

        return "redirect:/tipoEspacios";
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
}
