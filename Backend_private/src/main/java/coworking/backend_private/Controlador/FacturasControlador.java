package coworking.backend_private.Controlador;

import coworking.backend_private.Entidad.Factura;
import coworking.backend_private.Servicio.Interficie.IFacturasServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/facturas")
public class FacturasControlador {

    @Autowired
    private IFacturasServicio facturasServicio;

    @GetMapping("")
    public String getFacturas(Model model){
        List<Factura> verFacturas = facturasServicio.verTodos();

        model.addAttribute("nombre","Facturas");
        model.addAttribute("facturas",verFacturas);

        return "facturas/ver";
    }
}
