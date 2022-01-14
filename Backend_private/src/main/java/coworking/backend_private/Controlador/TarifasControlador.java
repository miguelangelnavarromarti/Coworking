package coworking.backend_private.Controlador;


import coworking.backend_private.Entidad.Tarifa;
import coworking.backend_private.Servicio.ITarifasServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/tarifas")
public class TarifasControlador {

    @Autowired
    private ITarifasServicio tarifasServicio;

    @GetMapping("/")
    public String listarTarifas(Model model){
        List<Tarifa> listarTarifas = tarifasServicio.listarTodo();
        model.addAttribute("tituloLista","Lista de tarifas");
        model.addAttribute("tabla",listarTarifas);
        return "tarifas/ver";
    }

    @GetMapping("/crear")
    public String crear(Model model){
        Tarifa tarifa = new Tarifa();
        model.addAttribute("titulo","Insertar Tarifa");
        model.addAttribute("tarifa", tarifa);
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

        Tarifa tarifaPrecioModificado = tarifasServicio.buscarPorId(tarifa.getCodigo());
        tarifaPrecioModificado.setPrecio(tarifa.getPrecio());
        tarifasServicio.guardar(tarifaPrecioModificado);

        return "redirect:/tarifas/";
    }

    @PostMapping("/comprobar")
    public String comprobar (@ModelAttribute Tarifa tarifa){

        //FICAR UN @TRNSACTIONAL EN ES METODE GUARDAR, aixo diu que o se fa tot o torna atras

        if (tarifasServicio.comprobar(tarifa) == 0){
            return guardar(tarifa);
        }else {
            return "redirect:/tarifas/crear";
        }
    }

    @GetMapping("/editar/{codigo}")
    public String editar(@PathVariable("codigo") Integer codigo,  Model model){
        Tarifa tarifa = tarifasServicio.buscarPorId(codigo);

        model.addAttribute("titulo","Editar Tarifa");
        model.addAttribute("tarifa", tarifa);
        return "tarifas/crear";
    }

    @GetMapping("/editarDefecto/{codigo}")
    public String editarDefecto(@PathVariable("codigo") Integer codigo,  Model model){
        Tarifa tarifa = tarifasServicio.buscarPorId(codigo);

        model.addAttribute("titulo","Editar Tarifa por defecto");
        model.addAttribute("tarifa", tarifa);
        return "tarifas/editarDefecto";
    }

}
