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
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
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

    public BloqueoEspacioHora comprobacion (@ModelAttribute BloqueoEspacioHora bloqueoEspacioHora) {
        List<BloqueoEspacioHora> lista = bloqueosEspaciosHorasServicio.verBloqueos();

        for (BloqueoEspacioHora bloqueo : lista) {
            boolean codigoEspacioYHora =  (bloqueo.getCodigoEspacio() == bloqueoEspacioHora.getCodigoEspacio()) && (bloqueo.getHora() == bloqueoEspacioHora.getHora());
            boolean dia = (bloqueo.getDiaBloqueo().getYear() == bloqueoEspacioHora.getDiaBloqueo().getYear() && bloqueo.getDiaBloqueo().getMonth() == bloqueoEspacioHora.getDiaBloqueo().getMonth() && bloqueo.getDiaBloqueo().getDayOfMonth() == bloqueoEspacioHora.getDiaBloqueo().getDayOfMonth());
            if (codigoEspacioYHora && dia) {
                return null;
            }
        }
        return bloqueoEspacioHora;
    }

    @PostMapping("/guardar")
    public String guardarBloqueo(@ModelAttribute BloqueoEspacioHora bloqueo) {

        BloqueoEspacioHora bloqueoValido = comprobacion(bloqueo);

        if (bloqueoValido == null) {
            return "redirect:/bloqueos";
        } else {
            bloqueosEspaciosHorasServicio.guardar(bloqueoValido);
            return "redirect:/bloqueos";
        }
    }

    @GetMapping("/modificar/{codigo}")
    public String modificarBloqueo(@PathVariable("codigo") Integer codigo, Model model) {

        BloqueoEspacioHora bloqueo = bloqueosEspaciosHorasServicio.buscarPorCodigo(codigo);

        model.addAttribute("codigo", codigo);

        model.addAttribute("nombre", "Bloqueo");
        model.addAttribute("bloqueo", bloqueo);

        List<Espacio> espacios = espaciosServicio.listaEspacio();
        model.addAttribute("espacios", espacios);

        List<HorarioDisponible> horas = horariosDisponiblesServicio.verHorarioDisponible();
        model.addAttribute("horario", horas);

        return "bloqueos/modificar";
    }

    @GetMapping("/eliminar/{codigo}")
    public String eliminar(@PathVariable("codigo") Integer codigo){

        bloqueosEspaciosHorasServicio.eliminarPorCodigo(codigo);

        return "redirect:/bloqueos";
    }

}
