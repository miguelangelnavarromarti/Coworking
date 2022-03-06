package coworking.backend_private.Controlador;


import coworking.backend_private.Entidad.Cliente;
import coworking.backend_private.Entidad.Reserva;
import coworking.backend_private.Servicio.Interficie.IClientesServicio;
import coworking.backend_private.Servicio.ReservasServicioImpl;
import org.apache.catalina.connector.Request;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/")
public class LoginControlador {

    @Autowired
    private IClientesServicio clientesServicio;
    @Autowired
    private ReservasServicioImpl reservasServicio;

    @GetMapping("")
    public String login(){
        return "login";
    }
    @GetMapping("/error")
    public String error(){
        return "error";
    }

    @RequestMapping("/guardarSesion")
    public String guardarSesion(@RequestParam ("usuario") String usuario, @RequestParam("password") String password,HttpServletRequest request, Model model){
        Cliente cliente = clientesServicio.verClientePorNombreUsuario(usuario);

        if (cliente != null){
            if(BCrypt.checkpw(password,cliente.getPassword()) && cliente.getRol().equals("administrador")){
                HttpSession session = request.getSession();
                session.setAttribute("usuario", cliente);
//------------------------------------------------
                List<Reserva> verReservas = reservasServicio.verReservas();

                model.addAttribute("nombre", "Reservas");
                model.addAttribute("reservas", verReservas);

                return "reservas/ver";
            } else {
                return "error";
            }
        } else {
            return "error";
        }
    }
}
