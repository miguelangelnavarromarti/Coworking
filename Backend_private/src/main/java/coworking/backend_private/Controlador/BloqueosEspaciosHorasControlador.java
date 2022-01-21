package coworking.backend_private.Controlador;

import coworking.backend_private.Entidad.BloqueoEspacioHora;
import coworking.backend_private.Entidad.Cliente;
import coworking.backend_private.Entidad.Espacio;
import coworking.backend_private.Entidad.HorarioDisponible;
import coworking.backend_private.Servicio.BloqueosEspaciosHorasServicioImpl;
import coworking.backend_private.Servicio.EspaciosServicioImpl;
import coworking.backend_private.Servicio.HorariosDisponiblesServicioImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/bloqueos")
public class BloqueosEspaciosHorasControlador {

    @Autowired
    BloqueosEspaciosHorasServicioImpl bloqueosEspaciosHorasServicio;

    @Autowired
    EspaciosServicioImpl espaciosServicio;

    @Autowired
    HorariosDisponiblesServicioImpl horariosDisponiblesServicio;

    @GetMapping("")
    public String getBloqueos(Model model){
        List<BloqueoEspacioHora> verBloqueos = bloqueosEspaciosHorasServicio.verBloqueos();

        model.addAttribute("nombre", "Bloqueos");
        model.addAttribute("bloqueos", verBloqueos);

        return "bloqueos/ver";
    }

    @GetMapping("/crear")
    public String crearBloqueo(Model model) {

        BloqueoEspacioHora bloqueo = new BloqueoEspacioHora();

        model.addAttribute("nombre", "Bloqueo");
        model.addAttribute("bloqueo", bloqueo);

        List<Espacio> espacios = espaciosServicio.listaEspacio();
        model.addAttribute("espacios", espacios);

        List<HorarioDisponible> horas = horariosDisponiblesServicio.verHorarioDisponible();
        model.addAttribute("horario", horas);

        return "bloqueos/crear";
    }
}
