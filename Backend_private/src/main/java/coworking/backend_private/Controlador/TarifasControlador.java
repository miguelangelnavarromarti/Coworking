package coworking.backend_private.Controlador;


import coworking.backend_private.Entidad.Tarifa;
import coworking.backend_private.Entidad.TipoEspacio;
import coworking.backend_private.Servicio.Interficie.ITarifasServicio;
import coworking.backend_private.Servicio.Interficie.ITipoEspaciosServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/tarifas")
public class TarifasControlador {

    @Autowired
    private ITarifasServicio tarifasServicio;

    @Autowired
    private ITipoEspaciosServicio tipoEspacioServicio;

    @GetMapping("")
    public String listarTarifas(Model model){
        List<Tarifa> listarTarifas = tarifasServicio.verTodo();
        model.addAttribute("nombre","Tarifas");
        model.addAttribute("tabla",listarTarifas);
        return "tarifas/ver";
    }

    @GetMapping("{codigo}")
    public String getTarifaPorTipoEspacio(Model model, @PathVariable("codigo") String codigo){
        TipoEspacio tipoEspacio = tipoEspacioServicio.verTipoEspacio(codigo);
        List<Tarifa> listarTarifas = tarifasServicio.verTarifasPorTipoEspacio(tipoEspacio);
        model.addAttribute("nombre","Tarifas");
        model.addAttribute("tabla",listarTarifas);
        return "tarifas/ver";
    }

    @GetMapping("/crear")
    public String crear(Model model){
        Tarifa tarifa = new Tarifa();

        List<TipoEspacio> listaTipoEspacio = tipoEspacioServicio.listaTipoEspacio();

        model.addAttribute("titulo","Insertar Tarifa");
        model.addAttribute("tarifa", tarifa);
        model.addAttribute("listaTipoEspacio",listaTipoEspacio);
        return "tarifas/crear";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Tarifa tarifa, RedirectAttributes attribute){

        if (existeCodigo(tarifa.getCodigo())){
            if (comprobarSolapamientoFechasConCodigo(tarifa)) {
                attribute.addFlashAttribute("success", "La Tarifa se ha guardado con éxito");
                tarifasServicio.guardar(tarifa);
                return "redirect:/tarifas";
            }
        }
        if (comprobarSolapamientoFechas(tarifa)) {
            attribute.addFlashAttribute("success", "La Tarifa se ha guardado con éxito");
            tarifasServicio.guardar(tarifa);
            return "redirect:/tarifas";
        }

        attribute.addFlashAttribute("error", "La Tarifa no se ha guaradado. Las fechas se solapan con otra Tarifa existente");
        return "redirect:/tarifas";

    }

    //  CONTINUAR AQUÍ, FALTA REFACTORIZAR I PONER ALERTS!!

    @PostMapping("/guardarPorDefecto")
    public String guardarPorDefecto(@ModelAttribute Tarifa tarifa, RedirectAttributes attribute){

        Tarifa tarifaPrecioModificado = tarifasServicio.buscarPorCodigo(tarifa.getCodigo());
        tarifaPrecioModificado.setPrecio(tarifa.getPrecio());
        tarifasServicio.guardar(tarifaPrecioModificado);
        attribute.addFlashAttribute("success", "La Tarifa se ha guardado con éxito");

        return "redirect:/tarifas/";
    }

    private boolean comprobarSolapamientoFechas (Tarifa tarifa){
        return tarifasServicio.comprobar(tarifa) == 0;
    }

    private boolean existeCodigo(Integer codigo) {
        List<Tarifa> tarifas = tarifasServicio.verTodo();
        for(Tarifa t : tarifas) {
            if(t.getCodigo() == codigo) {
                return true;
            }
        }
        return false;
    }

    private boolean comprobarSolapamientoFechasConCodigo(Tarifa tarifa) {
        return tarifasServicio.comprobarConCodigo(tarifa) == 0;
    }

    @GetMapping("/modificar/{codigo}")
    public String modificar(@PathVariable("codigo") Integer codigo,  Model model){
        Tarifa tarifa = tarifasServicio.buscarPorCodigo(codigo);
        List<TipoEspacio> listaTipoEspacio = tipoEspacioServicio.listaTipoEspacio();

        model.addAttribute("titulo","Modificar Tarifa");
        model.addAttribute("tarifa", tarifa);
        model.addAttribute("listaTipoEspacio",listaTipoEspacio);
        return "tarifas/modificar";
    }

    @GetMapping("/modificarDefecto/{codigo}")
    public String editarDefecto(@PathVariable("codigo") Integer codigo,  Model model){
        Tarifa tarifa = tarifasServicio.buscarPorCodigo(codigo);
        List<TipoEspacio> listaTipoEspacio = tipoEspacioServicio.listaTipoEspacio();

        model.addAttribute("titulo","Modificar Tarifa por defecto");
        model.addAttribute("tarifa", tarifa);
        model.addAttribute("listaTipoEspacio",listaTipoEspacio);
        return "/tarifas/modificarDefecto";
    }

}
