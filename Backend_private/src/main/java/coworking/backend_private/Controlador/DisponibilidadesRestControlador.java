package coworking.backend_private.Controlador;

import coworking.backend_private.Entidad.BloqueoEspacioHora;
import coworking.backend_private.Entidad.Espacio;
import coworking.backend_private.Entidad.HorarioDisponible;
import coworking.backend_private.Entidad.Reserva;
import coworking.backend_private.Servicio.BloqueosEspaciosHorasServicioImpl;
import coworking.backend_private.Servicio.EspaciosServicioImpl;
import coworking.backend_private.Servicio.HorariosDisponiblesServicioImpl;
import coworking.backend_private.Servicio.ReservasServicioImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/disponibilidadJson")
public class DisponibilidadesRestControlador {

    @Autowired
    private ReservasServicioImpl reservasServicio;

    @Autowired
    private EspaciosServicioImpl espaciosServicio;

    @Autowired
    private BloqueosEspaciosHorasServicioImpl bloqueosEspaciosHorasServicio;

    @Autowired
    private HorariosDisponiblesServicioImpl horariosDisponiblesServicio;

    @GetMapping("/{codigoEspacio}/{stringDia}")
    public HashMap<Integer, String> jsonDisponibilidad(@PathVariable("codigoEspacio") Integer codigoEspacio, @PathVariable("stringDia") String stringDia) {
    LocalDate dia = LocalDate.parse(stringDia);

    HashMap<Integer, String> disponibilidad = new HashMap<>();

    List<HorarioDisponible> listaHoras = horariosDisponiblesServicio.verHorarioDisponible();
    for(HorarioDisponible horario : listaHoras) {
        disponibilidad.put(horario.getHora(), "VACIO");
    }

    Espacio espacio = espaciosServicio.verEspacio(codigoEspacio);
    List<Reserva> listaReservas = reservasServicio.verReservasEspacioYDia(espacio, dia);
    for (Reserva reserva : listaReservas) {
        if (!reserva.getEstado().equals("cancelado")) {
            disponibilidad.put(reserva.getHorarioDisponible().getHora(), reserva.getCliente().getNombreUsuario());
        }
    }

    List<BloqueoEspacioHora> listaBloqueos = bloqueosEspaciosHorasServicio.verPorEspacioYDia(espacio, dia);
    for (BloqueoEspacioHora bloqueo : listaBloqueos) {
        disponibilidad.put(bloqueo.getHora().getHora(), "BLOQUEADO");
    }

    return disponibilidad;
    }
}
