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

    @GetMapping("/editar/{codigo}")
    public String editar(@PathVariable("codigo") Integer codigo,  Model model){
        Tarifa tarifa = tarifasServicio.buscarPorId(codigo);

        model.addAttribute("titulo","Editar Tarifa");
        model.addAttribute("tarifa", tarifa);
        return "tarifas/crear";
    }

}
