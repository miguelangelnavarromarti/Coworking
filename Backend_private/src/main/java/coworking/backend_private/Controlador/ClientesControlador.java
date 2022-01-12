package coworking.backend_private.Controlador;

import coworking.backend_private.Entidad.Cliente;
import coworking.backend_private.Servicio.IClientesServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClientesControlador {

    @Autowired
    private IClientesServicio clientesServicio;

    @GetMapping("")
    public String getClientes(Model model){
        List<Cliente> verClientes = clientesServicio.verTodos();

        model.addAttribute("titulo", "Listado de Clientes");
        model.addAttribute("clientes", verClientes);

        return "clientes/ver";
    }

    @GetMapping("/crear")
    public String crearCliente(Model model) {

        Cliente cliente = new Cliente();

        model.addAttribute("titulo", "Crear cliente");
        model.addAttribute("cliente", cliente);

        return "clientes/crear";
    }

    @PostMapping("/guardar")
    public String guardarCliente(@ModelAttribute Cliente cliente) {

        clientesServicio.guardar(cliente);
        System.out.println("Cliente guardado con éxito");
        return "redirect:/clientes";
    }

    @GetMapping("/modificar/{codigo}")
    public String modificarCliente(@PathVariable("codigo") Integer codigo, Model model) {

        //CONTINUAR AQUÍ - AFEGIR ANAR A RESERVES DEL CLIENT I A LES SEVES FACTURES

        Cliente cliente = new Cliente();

        model.addAttribute("titulo", "Crear cliente");
        model.addAttribute("cliente", cliente);

        return "clientes/crear";
    }
}
