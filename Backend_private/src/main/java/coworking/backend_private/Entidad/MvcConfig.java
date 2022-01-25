package coworking.backend_private.Entidad;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/files/**")
                //.addResourceLocations("file:/Users/joangalmesriera/Downloads/photos/");
                .addResourceLocations("file:/Usuarios/Carles/Descargas/imgCoworking/");
    }


    /*
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        exposeDirectory("E:/DAW/imgCoworking/", registry);
    }

    private void exposeDirectory(String dirName, ResourceHandlerRegistry registry) {
        Path uploadDir = Paths.get(dirName);
        String uploadPath = uploadDir.toFile().getAbsolutePath();

        if (dirName.startsWith("../")) dirName = dirName.replace("../", "");

        registry.addResourceHandler("E://DAW/imgCoworking/**").addResourceLocations("/recursos/");
    }

     */
}