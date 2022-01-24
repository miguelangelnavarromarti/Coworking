package coworking.backend_private.Controlador;

import coworking.backend_private.Servicio.Interficie.IGestionCancelacionesServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gestionCancelaciones")
public class GestionCancelacionesControlador {

    @Autowired
    private IGestionCancelacionesServicio gestionCancelacionesServicio;



}
