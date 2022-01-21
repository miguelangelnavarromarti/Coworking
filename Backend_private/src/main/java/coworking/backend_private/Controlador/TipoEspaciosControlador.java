package coworking.backend_private.Controlador;

import coworking.backend_private.Entidad.*;
import coworking.backend_private.Servicio.IIdiomasServicio;
import coworking.backend_private.Servicio.ITarifasServicio;
import coworking.backend_private.Servicio.ITipoEspaciosServicio;
import coworking.backend_private.Servicio.ITraduccionesTipoEspaciosServicio;
import org.hibernate.tool.schema.extract.spi.TableInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
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
    public String guardarTipoEspacio(@ModelAttribute TipoEspacio tipoEspacio) {
        List<String> lista = tipoEspaciosServicio.listaCodigos();

        for (String codigo : lista) {
            if(Objects.equals(codigo, tipoEspacio.getCodigo())) {
                tipoEspaciosServicio.guardar(tipoEspacio);
                return "redirect:/tipoEspacios";
            }
        }

        tipoEspaciosServicio.guardar(tipoEspacio);

        List<Idioma> idiomas = idiomasServicio.listaIdiomas();
        for (int i = 0; i < idiomas.size(); i++) {
            TraduccionTipoEspacio traduccionTipoEspacio = new TraduccionTipoEspacio(tipoEspacio,idiomas.get(i),"");
            traduccionesTipoEspaciosServicio.guardar(traduccionTipoEspacio);
        }

        return "redirect:/traduccionesTipoEspacios/" + tipoEspacio.getCodigo();
    }

    @PostMapping("/guardarConTarifa")
    public String guardarTipoEspacioConTarifa(@ModelAttribute TipoEspacio tipoEspacio, @RequestParam(required = false) double precio, @RequestParam(required = false) String hoy) {
        List<String> lista = tipoEspaciosServicio.listaCodigos();

        for (String codigo : lista) {
            if(Objects.equals(codigo, tipoEspacio.getCodigo())) {
                tipoEspaciosServicio.guardar(tipoEspacio);
                return "redirect:/tipoEspacios";
            }
        }

        tipoEspaciosServicio.guardar(tipoEspacio);
        Tarifa tarifa = new Tarifa(
                tipoEspacio.getCodigo(),
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

        return "tipoEspacios/modificar";
    }
}
