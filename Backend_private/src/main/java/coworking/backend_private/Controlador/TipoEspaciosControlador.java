package coworking.backend_private.Controlador;

import coworking.backend_private.Entidad.*;
import coworking.backend_private.Servicio.Interficie.IIdiomasServicio;
import coworking.backend_private.Servicio.Interficie.ITarifasServicio;
import coworking.backend_private.Servicio.Interficie.ITipoEspaciosServicio;
import coworking.backend_private.Servicio.Interficie.ITraduccionesTipoEspaciosServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/tipoEspacios")
public class TipoEspaciosControlador {

    @Autowired
    private ITipoEspaciosServicio tipoEspaciosServicio;

    @Autowired
    private ITraduccionesTipoEspaciosServicio traduccionesTipoEspaciosServicio;

    @Autowired
    private IIdiomasServicio idiomasServicio;

    @Autowired
    private ITarifasServicio tarifasServicio;

    @GetMapping("")
    public String getTipoEspacios(Model model){
        List<TipoEspacio> verTipoEspacios = tipoEspaciosServicio.listaTipoEspacio();

        model.addAttribute("nombre", "TipoEspacios");
        model.addAttribute("tipoEspacios", verTipoEspacios);

        return "tipoEspacios/ver";
    }
    /*
    @GetMapping("/{codigo}")
    public String getTipoEspacio(@PathVariable("codigo") String codigo, Model model){

        model.addAttribute("nombre", "TipoEspacios");
        model.addAttribute("codigo", codigo);

        TipoEspacio tipoEspacio = tipoEspaciosServicio.verTipoEspacio(codigo);
        model.addAttribute("tipoEspacio", tipoEspacio);

        List<TraduccionTipoEspacio> verTraduccionesTipoEspacios = traduccionesTipoEspaciosServicio.verTraduccionesPorCodigoTipoEspacio(codigo);
        model.addAttribute("traduccionTipoEspacio", verTraduccionesTipoEspacios);

        return "tipoEspacios/ficha";
    }*/

    @GetMapping("/crear")
    public String crearTipoEspacio(Model model) {

        TipoEspacio tipoEspacio = new TipoEspacio();
        Tarifa tarifa = new Tarifa();

        model.addAttribute("nombre", "Tipo Espacio");
        model.addAttribute("tipoEspacio", tipoEspacio);
        model.addAttribute("tarifa", tarifa);

        return "tipoEspacios/crear";
    }

    @PostMapping("/guardar")
    public String guardarTipoEspacio(@ModelAttribute TipoEspacio tipoEspacio, RedirectAttributes attribute) {

        tipoEspaciosServicio.guardar(tipoEspacio);

        attribute.addFlashAttribute("success", "El Tipo Espacio se ha guardado con éxito");
        return "redirect:/tipoEspacios";
    }

    @PostMapping("/guardarConTarifa")
    public String guardarTipoEspacioConTarifa(@ModelAttribute TipoEspacio tipoEspacio, @RequestParam(required = false) double precio, @RequestParam(required = false) String hoy, RedirectAttributes attribute) {
        if (comprobacionCodigo(tipoEspacio)) {
            attribute.addFlashAttribute("error", "Ese código para ese Tipo de Espacio ya existe");
            return "redirect:/tipoEspacios";
        }

        tipoEspaciosServicio.guardar(tipoEspacio);
        Tarifa tarifa = new Tarifa(
                tipoEspacio,
                precio,
                LocalDate.parse(hoy),
                null,
                true
        );
        tarifasServicio.guardar(tarifa);


        List<Idioma> idiomas = idiomasServicio.listaIdiomas();
        for (int i = 0; i < idiomas.size(); i++) {
            TraduccionTipoEspacio traduccionTipoEspacio = new TraduccionTipoEspacio(tipoEspacio,idiomas.get(i),"");
            traduccionesTipoEspaciosServicio.guardar(traduccionTipoEspacio);
        }

        attribute.addFlashAttribute("success", "El Tipo Espacio se ha guardado con éxito");
        attribute.addFlashAttribute("warning", "Se ha generado la tarifa automáticamente. Guardada con éxito");
        return "redirect:/traduccionesTipoEspacios/" + tipoEspacio.getCodigo();
    }


    private boolean comprobacionCodigo(TipoEspacio tipoEspacio) {

        List<String> listaCodigos = tipoEspaciosServicio.listaCodigos();

        for (String codigo : listaCodigos) {
            if (Objects.equals(tipoEspacio.getCodigo(), codigo)) {
                return true;
            }
        }

        return false;
    }

    @GetMapping("/modificar/{codigo}")
    public String modificarTipoEspacio(@PathVariable("codigo") String codigo, Model model) {

        TipoEspacio tipoEspacio = tipoEspaciosServicio.verTipoEspacio(codigo);

        model.addAttribute("nombre", "Tipo Espacio");
        model.addAttribute("tipoEspacio", tipoEspacio);

        return "tipoEspacios/modificar";
    }
}
