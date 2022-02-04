package coworking.backend_private.Controlador;

import coworking.backend_private.Entidad.Cliente;
import coworking.backend_private.Servicio.Interficie.IClientesServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String guardarCliente(@ModelAttribute Cliente cliente, RedirectAttributes attribute) {

        List<Integer> listaCodigos = clientesServicio.verCodigos();
        List<String> listaNombresUsuarios = clientesServicio.listarNombresUsuarios();
        List<String> listaEmails = clientesServicio.listarEmails();

        boolean exist = false;

        for (Integer codigo : listaCodigos) {
            if (Objects.equals(codigo, cliente.getCodigo())) {
                exist = true;
                break;
            }
        }

        if (!exist) {
            for (String nombreUsuario : listaNombresUsuarios) {
                if (Objects.equals(cliente.getNombreUsuario(), nombreUsuario)) {
                    attribute.addFlashAttribute("error", "Este nombre de Usuario '" + cliente.getNombreUsuario() + "' ya existe");
                    return "redirect:/clientes/crear";
                }
            }
            for (String emails : listaEmails) {
                if (Objects.equals(cliente.getEmail(), emails)) {
                    attribute.addFlashAttribute("error", "Este email '" + cliente.getEmail() + "' ya está en uso");
                    return "redirect:/clientes/crear";
                }
            }
        }

        clientesServicio.guardar(cliente);
        attribute.addFlashAttribute("success", "Cliente guardado con éxito");

        return "redirect:/clientes";
        /* SEGUIR AQUÍ, si es codi esta dins sa llista, guardar. Si no esta dins sa llista, entrar a ses comprovacions */
    }

    @GetMapping("/modificar/{codigo}")
    public String modificarCliente(@PathVariable("codigo") Integer codigo, Model model) {

        Cliente cliente = clientesServicio.buscarPorCodigo(codigo);

        model.addAttribute("nombre", "Cliente");
        model.addAttribute("cliente", cliente);

        return "clientes/modificar";
    }
}
