package coworking.backend_private.Configuracion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

    @Value("${rutes.fotos}")
    String rutaProperties;

    @Autowired
    @Qualifier("Interceptor")
    private HandlerInterceptor miInterceptor;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/files/**")
                .addResourceLocations(rutaProperties);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(miInterceptor).addPathPatterns(
                "/main/**",
                "/bloqueos/**",
                "/clientes/**",
                "/disponiilidad/**",
                "/espacios/**",
                "/facturas/**",
                "/facturasCanceladas/**",
                "/gestionCancelaciones/**",
                "/Idiomas/**",
                "/gestionOfertas/**",
                "/imagenes/**",
                "/opiniones/**",
                "/reservas/**",
                "/tarifas/**",
                "/tipoEspacios/**",
                "/traduccionesEspacios/**",
                "/traduccionesTipoEspacios/**");
    }

}