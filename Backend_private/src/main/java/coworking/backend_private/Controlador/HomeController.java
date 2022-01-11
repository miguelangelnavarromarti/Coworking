package coworking.backend_private.Controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/patata")
    public String getHome(){
        return "home";
    }
}
