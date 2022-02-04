package coworking.backend_private.Controlador;

import coworking.backend_private.Entidad.Factura;
import coworking.backend_private.Entidad.FacturaCancelacion;
import coworking.backend_private.Entidad.GestionCancelacion;
import coworking.backend_private.Entidad.Reserva;
import coworking.backend_private.Servicio.Interficie.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

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

    @Autowired
    IReservasServicio reservasServicio;

    @GetMapping("")
    public String getFacturasCanceladas(Model model){
        List<FacturaCancelacion> verFacturasCancelacion = facturasCancelacionesServicio.verFacturasCancelaciones();

        model.addAttribute("nombre", "FacturasCancelacion");
        model.addAttribute("facturasCancelacion", verFacturasCancelacion);

        return "facturasCanceladas/ver";
    }

    @GetMapping("/crear/{codigoFactura}")
    public String crearFacturaCancelada(@PathVariable("codigoFactura") Integer codigoFactura, RedirectAttributes attribute){
        Factura factura = facturasServicio.buscarPorCodigo(codigoFactura);
        List<GestionCancelacion> politicaCancelacion = gestionCancelacionesServicio.verTodoOrdenadoPorDiaAntelacionAsc();

        LocalDate hoy = LocalDate.now();
        double devolucion = 0;
        Integer diasAntelacionCancelacion = 0;
        Integer descuentoCancelacion = 0;

        /* Comprueba que política de cancelación aplicar */
        for (GestionCancelacion gc : politicaCancelacion) {
            if(DAYS.between(factura.getDiaFactura(), hoy) >= gc.getDiasAntelacion()) {
                diasAntelacionCancelacion = gc.getDiasAntelacion();
                descuentoCancelacion = gc.getDevolucion();
            } else {
                break;
            }
        }

        devolucion = (factura.getPrecioTotal() * ((float)descuentoCancelacion / 100));

        /* Generamos la factura de cancelación (lo hacemos así porque con el constructor no funciona) */
        FacturaCancelacion facturaCancelacion = new FacturaCancelacion();
        facturaCancelacion.setCodigoFactura(factura);
        facturaCancelacion.setCodigoCliente(factura.getCodigoCliente());
        facturaCancelacion.setDevolucion(devolucion);
        facturaCancelacion.setDiasAntelacionCancelacion(diasAntelacionCancelacion);
        facturaCancelacion.setDescuentoCancelacion(descuentoCancelacion);

        attribute.addFlashAttribute("success", "Se ha generado la factura de cancelación con éxito");
        facturasCancelacionesServicio.guardar(facturaCancelacion);


        List<Integer> reservas = reservasServicio.verCodigoReservasPorCodigoFactura(factura.getCodigo());

        for(Integer codigoReserva : reservas) {
            Reserva reserva = reservasServicio.verReservaPorCodigo(codigoReserva);
            reserva.setEstado("cancelado");
            reservasServicio.guardar(reserva);
        }
        attribute.addFlashAttribute("info", "Se han cancelado las reservas asociadas automáticamente");

        return "redirect:/facturasCanceladas";
    }

    @GetMapping("/verFactura/{codigo}")
    public String modificar (@PathVariable("codigo") Integer codigo, Model model){
        FacturaCancelacion facturaCancelacion = facturasCancelacionesServicio.verFacturaCanceladaPorCodigo(codigo);
        model.addAttribute("nombre","FacturaCancelada");
        model.addAttribute("facturaCancelada", facturaCancelacion);

        return "facturasCanceladas/verFactura";
    }
}
