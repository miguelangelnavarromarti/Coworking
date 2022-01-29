package coworking.backend_private.Controlador;

import coworking.backend_private.Entidad.Factura;
import coworking.backend_private.Servicio.Interficie.IFacturasServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/guardar")
    public String guardarFactura(@ModelAttribute Factura factura){

        facturasServicio.guardar(factura);
        return "redirect:/facturas";
    }

    @GetMapping("/modificar/{codigo}")
    public String modificar (@PathVariable("codigo") Integer codigo, Model model){
        Factura factura = facturasServicio.buscarPorCodigo(codigo);
        model.addAttribute("nombre","Factura");
        model.addAttribute("factura",factura);
        return "facturas/modificar";
    }


}
