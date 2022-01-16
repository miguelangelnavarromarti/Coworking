package coworking.backend_private.Controlador;


import coworking.backend_private.Entidad.Tarifa;
import coworking.backend_private.Entidad.TipoEspacio;
import coworking.backend_private.Servicio.ITarifasServicio;
import coworking.backend_private.Servicio.ITipoEspaciosServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String guardar(@ModelAttribute Tarifa tarifa){

        tarifasServicio.guardar(tarifa);
        return "redirect:/tarifas/";
    }

    /*@PostMapping("/guardarPorDefecto")
    public String guardarPorDefecto(@ModelAttribute Integer codigo, @ModelAttribute double precio){

        Tarifa tarifa = tarifasServicio.buscarPorId(codigo);
        tarifa.setPrecio(precio);
        tarifasServicio.guardar(tarifa);

        return "redirect:/tarifas/";

    }*/

    @PostMapping("/guardarPorDefecto")
    public String guardarPorDefecto(@ModelAttribute Tarifa tarifa){

        Tarifa tarifaPrecioModificado = tarifasServicio.buscarPorCodigo(tarifa.getCodigo());
        tarifaPrecioModificado.setPrecio(tarifa.getPrecio());
        tarifasServicio.guardar(tarifaPrecioModificado);

        return "redirect:/tarifas/";
    }

    @PostMapping("/comprobar")
    public String comprobar (@ModelAttribute Tarifa tarifa){



        if (tarifasServicio.comprobar(tarifa) == 0){
            return guardar(tarifa);
        }else {
            return "redirect:/tarifas/crear";
        }
    }

    @GetMapping("/editar/{codigo}")
    public String editar(@PathVariable("codigo") Integer codigo,  Model model){
        Tarifa tarifa = tarifasServicio.buscarPorCodigo(codigo);
        List<TipoEspacio> listaTipoEspacio = tipoEspacioServicio.listaTipoEspacio();

        model.addAttribute("titulo","Editar Tarifa");
        model.addAttribute("tarifa", tarifa);
        model.addAttribute("listaTipoEspacio",listaTipoEspacio);
        return "tarifas/crear";
    }

    @GetMapping("/editarDefecto/{codigo}")
    public String editarDefecto(@PathVariable("codigo") Integer codigo,  Model model){
        Tarifa tarifa = tarifasServicio.buscarPorCodigo(codigo);
        List<TipoEspacio> listaTipoEspacio = tipoEspacioServicio.listaTipoEspacio();

        model.addAttribute("titulo","Editar Tarifa por defecto");
        model.addAttribute("tarifa", tarifa);
        model.addAttribute("listaTipoEspacio",listaTipoEspacio);
        return "tarifas/editarDefecto";
    }

}
