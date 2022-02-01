package coworking.backend_private.Controlador;

import coworking.backend_private.Entidad.Factura;
import coworking.backend_private.Entidad.FacturaCancelacion;
import coworking.backend_private.Servicio.Interficie.IFacturasCancelacionesServicio;
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

    @Autowired
    IFacturasCancelacionesServicio facturasCancelacionesServicio;

    @GetMapping("")
    public String getFacturas(Model model){
        List<Factura> verFacturas = facturasServicio.verTodos();

        model.addAttribute("nombre","Facturas");
        model.addAttribute("facturas",verFacturas);

        List<Integer> facturasConFacturasCanceladas = facturasServicio.verFacturasConFacturaCancelada();
        model.addAttribute("facturasConFacturasCanceladas", facturasConFacturasCanceladas);

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
        model.addAttribute("codigoFactura", codigo);

        FacturaCancelacion facturaCancelada = facturasCancelacionesServicio.verFacturaCancelacionPorFactura(factura);
        if (facturaCancelada != null) {
            model.addAttribute("booleanFacturaCancelada", true);
        } else {
            model.addAttribute("booleanFacturaCancelada", false);
        }
        return "facturas/modificar";
    }

    @GetMapping("/{codigo}")
    public String getFactura (@PathVariable("codigo") Integer codigo, Model model){
        Factura factura = facturasServicio.buscarPorCodigo(codigo);
        model.addAttribute("nombre","Factura");
        model.addAttribute("factura",factura);

        return "facturas/verFactura";
    }
}
