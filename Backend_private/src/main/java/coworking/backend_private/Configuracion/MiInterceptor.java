package coworking.backend_private.Configuracion;


import coworking.backend_private.Entidad.Cliente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component("Interceptor")
public class MiInterceptor implements HandlerInterceptor {
    private static final Logger log = (Logger) LoggerFactory.getLogger(MiInterceptor.class);


    @Override
    public boolean preHandle (HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        log.info("[preHandle][" + request + "] [" + request.getMethod() + "]" + request.getRequestURI());
        HttpSession session = request.getSession(false);
        //String usuario = (String) session.getAttribute("usuario");
        Cliente usuario = (Cliente) session.getAttribute("usuario");
        if(usuario==null){
            response.sendRedirect("/error");
            return false;
        } else {
            return true;
        }
    }

}
