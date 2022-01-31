package coworking.backend_private.Controlador;

import coworking.backend_private.Entidad.Cliente;
import coworking.backend_private.Entidad.Factura;
import coworking.backend_private.Entidad.FacturaCancelacion;
import coworking.backend_private.Entidad.GestionCancelacion;
import coworking.backend_private.Servicio.Interficie.IClientesServicio;
import coworking.backend_private.Servicio.Interficie.IFacturasCancelacionesServicio;
import coworking.backend_private.Servicio.Interficie.IFacturasServicio;
import coworking.backend_private.Servicio.Interficie.IGestionCancelacionesServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/facturasCanceladas")
public class FacturasCancelacionesControlador {

    @Autowired
    private IFacturasCancelacionesServicio facturasCancelacionesServicio;

    @Autowired
    IFacturasServicio facturasServicio;

    @Autowired
    IGestionCancelacionesServicio gestionCancelacionesServicio;

    @Autowired
    IClientesServicio clientesServicio;

    @GetMapping("")
    public String getFacturasCanceladas(Model model){
        List<FacturaCancelacion> verFacturasCancelacion = facturasCancelacionesServicio.verFacturasCancelaciones();

        model.addAttribute("nombre", "FacturasCancelacion");
        model.addAttribute("facturasCancelacion", verFacturasCancelacion);

        return "facturasCanceladas/ver";
    }

    @GetMapping("/crear/{codigoFactura}")
    public String crearFacturaCancelada(@PathVariable("codigoFactura") Integer codigoFactura){
        Factura factura = facturasServicio.buscarPorCodigo(codigoFactura);
        List<GestionCancelacion> politicaCancelacion = gestionCancelacionesServicio.verTodoOrdenadoPorDiaAntelacionAsc();

        LocalDate hoy = LocalDate.now();
        double devolucion = 0;
        Integer diasAntelacionCancelacion = 0;
        Integer descuentoCancelacion = 0;

        for (GestionCancelacion gc : politicaCancelacion) {
            if(hoy.compareTo(factura.getDiaFactura()) >= gc.getDiasAntelacion()) {
                diasAntelacionCancelacion = gc.getDiasAntelacion();
                descuentoCancelacion = gc.getDevolucion();
            } else {
                break;
            }
        }

        devolucion = (factura.getPrecioTotal() * ((float)descuentoCancelacion / 100));

        // ALGO FALLA QUAN GUARDAM
        FacturaCancelacion facturaCancelacion = new FacturaCancelacion(
                factura,
                factura.getCodigoCliente(),
                devolucion,
                diasAntelacionCancelacion,
                descuentoCancelacion
        );

        facturasCancelacionesServicio.guardar(facturaCancelacion);

        return "redirect:/facturasCanceladas";
    }
}
