package coworking.backend_private.Controlador;

import coworking.backend_private.Entidad.Clientes;
import coworking.backend_private.Servicio.IClientesServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClientesControlador {

    @Autowired
    private IClientesServicio clientesServicio;

    @GetMapping("")
    public String getClientes(Model model){
        List<Clientes> verClientes = clientesServicio.verTodos();

        model.addAttribute("titulo", "Listado de Clientes");
        model.addAttribute("clientes", verClientes);

        return "clientes/ver";
    }

    @GetMapping("/crear")
    public String crearCliente(Model model) {

        Clientes cliente = new Clientes();

        model.addAttribute("titulo", "Crear cliente");
        model.addAttribute("cliente", cliente);

        return "clientes/crear";
    }

    @PostMapping("/guardar")
    public String guardarCliente(@ModelAttribute Clientes cliente) {

        clientesServicio.guardar(cliente);
        System.out.println("Cliente guardado con éxito");
        return "redirect:/clientes";
    }
}
