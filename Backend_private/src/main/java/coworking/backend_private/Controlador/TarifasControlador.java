package coworking.backend_private.Controlador;


import coworking.backend_private.Entidad.Tarifas;
import coworking.backend_private.Servicio.ITarifasServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/tarifas")
public class TarifasControlador {

    @Autowired
    private ITarifasServicio tarifasServicio;

    @GetMapping("/")
    public String listarTarifas(Model model){
        List<Tarifas> listarTarifas = tarifasServicio.listarTodo();
        model.addAttribute("tituloLista","Lista de tarifas");
        model.addAttribute("tabla",listarTarifas);
        return "lista";
    }
}
