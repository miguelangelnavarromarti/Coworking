package coworking.backend_private.Entidad;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

    //@Value("${<rutes.fotos>}")

    //@Value("${rutes.fotos}")
    @Value("${rutes.fotos}")
    String rutaProperties;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/files/**")
                //.addResourceLocations("file:/Users/joangalmesriera/Downloads/photos/");
                //.addResourceLocations("file:///E:/DAW/imgCoworking/");
                .addResourceLocations(rutaProperties);


    }

}