package coworking.backend_private.Controlador;

import coworking.backend_private.Entidad.Cliente;
import coworking.backend_private.Servicio.IClientesServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/clientes")
public class ClientesControlador {

    @Autowired
    private IClientesServicio clientesServicio;

    @GetMapping("")
    public String getClientes(Model model){
        List<Cliente> verClientes = clientesServicio.verTodos();

        model.addAttribute("nombre", "Clientes");
        model.addAttribute("clientes", verClientes);

        return "clientes/ver";
    }

    @GetMapping("/crear")
    public String crearCliente(Model model) {

        Cliente cliente = new Cliente();

        model.addAttribute("nombre", "Cliente");
        model.addAttribute("cliente", cliente);

        return "clientes/crear";
    }

    @PostMapping("/guardar")
    public String guardarCliente(@ModelAttribute Cliente cliente) {

        clientesServicio.guardar(cliente);
        System.out.println("Cliente guardado con éxito");

        return "redirect:/clientes";
    }

    @PostMapping("/comprobacionNombreUsuarioYEmail")
    public String comprobacionNombreUsuarioYEmail(@ModelAttribute Cliente cliente, Model model) {

        List<String> listaNombresUsuarios = clientesServicio.listarNombresUsuarios();
        List<String> listaEmails = clientesServicio.listarEmails();

        boolean comprobacionNombreUsuario = true;
        boolean comprobacionEmail = true;

        for (String nombreUsuario : listaNombresUsuarios) {
            if (Objects.equals(cliente.getNombreUsuario(), nombreUsuario)) {
                model.addAttribute("errorNombreUsuario", "Este nombre de Usuario '" + cliente.getNombreUsuario() + "' ya existe.");
                comprobacionNombreUsuario = false;
                break;
            }
        }

        for (String emails : listaEmails) {
            if (Objects.equals(cliente.getNombreUsuario(), emails)) {
                model.addAttribute("errorEmail", "Este email '" + cliente.getEmail() + "' ya está en uso.");
                comprobacionEmail = false;
                break;
            }
        }

        if (comprobacionNombreUsuario && comprobacionEmail) {
            return guardarCliente(cliente);
        } else {
            return "redirect:/clientes/crear";
        }
    }

    @GetMapping("/modificar/{codigo}")
    public String modificarCliente(@PathVariable("codigo") Integer codigo, Model model) {

        Cliente cliente = clientesServicio.buscarPorCodigo(codigo);

        model.addAttribute("nombre", "Cliente");
        model.addAttribute("cliente", cliente);

        return "clientes/modificar";
    }

    // CONTINUAR AQUÍ
    // COMPROVAR ERRORES FORMULARIO
    // SI SOBRA TEMPS, AFEGIR DATA DE NAIXEMENT I GÈNERE DE L'USUARI
    // AFEGIR ANAR A RESERVES DEL CLIENT I A LES SEVES FACTURES
}
