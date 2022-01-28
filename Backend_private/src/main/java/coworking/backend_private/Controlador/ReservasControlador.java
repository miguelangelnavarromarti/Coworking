package coworking.backend_private.Controlador;

import coworking.backend_private.Entidad.Cliente;
import coworking.backend_private.Entidad.Espacio;
import coworking.backend_private.Entidad.HorarioDisponible;
import coworking.backend_private.Entidad.Reserva;
import coworking.backend_private.Servicio.ClientesServicioImpl;
import coworking.backend_private.Servicio.EspaciosServicioImpl;
import coworking.backend_private.Servicio.HorariosDisponiblesServicioImpl;
import coworking.backend_private.Servicio.ReservasServicioImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/reservas")
public class ReservasControlador {

    @Autowired
    private ReservasServicioImpl reservasServicio;

    @Autowired
    private EspaciosServicioImpl espaciosServicio;

    @Autowired
    private HorariosDisponiblesServicioImpl horariosDisponiblesServicio;

    @Autowired
    private ClientesServicioImpl clientesServicio;

    @GetMapping("")
    public String getReserves(Model model){
        List<Reserva> verReservas = reservasServicio.verReservas();

        model.addAttribute("nombre", "Reservas");
        model.addAttribute("reservas", verReservas);

        return "reservas/ver";
    }

    @GetMapping("/modificar/{codigo}")
    public String modificarReserva(@PathVariable("codigo") Integer codigo, Model model) {

        Reserva reserva = reservasServicio.verReservaPorCodigo(codigo);

        model.addAttribute("nombre", "Reserva");
        model.addAttribute("reserva", reserva);

        List<Espacio> verEspacios = espaciosServicio.listaEspacio();
        model.addAttribute("espacios", verEspacios);

        List<HorarioDisponible> verHoras = horariosDisponiblesServicio.verHorarioDisponible();
        model.addAttribute("horas", verHoras);

        List<Cliente> verClientes = clientesServicio.verTodos();
        model.addAttribute("clientes", verClientes);

        return "reservas/modificar";
    }

    @PostMapping("/cancelar")
    public String cancelarReserva(@ModelAttribute Reserva reserva) {

        reserva.setEstado("cancelado");

        reservasServicio.guardar(reserva);
        System.out.println("Reserva cancelada con éxito");

        return "redirect:/reservas";
    }
}
