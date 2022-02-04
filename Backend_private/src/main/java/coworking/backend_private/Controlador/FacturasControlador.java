package coworking.backend_private.Controlador;

import coworking.backend_private.Entidad.Cliente;
import coworking.backend_private.Entidad.Factura;
import coworking.backend_private.Entidad.FacturaCancelacion;
import coworking.backend_private.Servicio.Interficie.IClientesServicio;
import coworking.backend_private.Servicio.Interficie.IFacturasCancelacionesServicio;
import coworking.backend_private.Servicio.Interficie.IFacturasServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/facturas")
public class FacturasControlador {

    @Autowired
    IFacturasServicio facturasServicio;

    @Autowired
    IFacturasCancelacionesServicio facturasCancelacionesServicio;

    @Autowired
    IClientesServicio clientesServicio;

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
    public String guardarFactura(@ModelAttribute Factura factura, RedirectAttributes attribute){

        facturasServicio.guardar(factura);
        attribute.addFlashAttribute("success", "Se ha guardado la Factura con éxito");
        return "redirect:/facturas";
    }

    @GetMapping("/{codigo}")
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
        return "facturas/verFactura";
    }

    @GetMapping("/usuario/{nombreUsuario}")
    public String getFacturaPorCliente (@PathVariable("nombreUsuario") String nombreUsuario, Model model){
        Cliente cliente = clientesServicio.verClientePorNombreUsuario(nombreUsuario);
        List<Factura> facturas = facturasServicio.verFacturaPorCliente(cliente);
        model.addAttribute("nombre","Factura");
        model.addAttribute("facturas",facturas);

        List<Integer> facturasConFacturasCanceladas = facturasServicio.verFacturasConFacturaCancelada();
        model.addAttribute("facturasConFacturasCanceladas", facturasConFacturasCanceladas);

        return "facturas/ver";
    }
}
