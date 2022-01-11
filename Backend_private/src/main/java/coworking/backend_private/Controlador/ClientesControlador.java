package coworking.backend_private.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClientesController {
    @GetMapping("clientes")
    public String getClientes(){ return "clientes"; }
}
