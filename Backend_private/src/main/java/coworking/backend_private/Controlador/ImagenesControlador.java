package coworking.backend_private.Controlador;

import coworking.backend_private.Entidad.Imagen;
import coworking.backend_private.Entidad.TipoEspacio;
import coworking.backend_private.Servicio.Interficie.IImagenesServicio;
import coworking.backend_private.Servicio.Interficie.ITipoEspaciosServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

@Controller
@RequestMapping("/imagenes")
public class ImagenesControlador {

    @Value("${rutes.fotos.controlador}")
    String ruta;

    @Autowired
    private ITipoEspaciosServicio tipoEspacioServicio;

    @Autowired
    private IImagenesServicio imagenesServicio;

    @GetMapping("")
    public String ver(Model model){
        List<Imagen> listaImagenes = imagenesServicio.verTodo();
        model.addAttribute("listaImagenes",listaImagenes);
        return "imagenes/ver";
    }

    @GetMapping("/formulario")
    public String formulario (Model model){
        Imagen imagen = new Imagen();
        List<TipoEspacio> listaTipoEspacio = tipoEspacioServicio.listaTipoEspacio();
        model.addAttribute("listaTipoEspacio",listaTipoEspacio);
        model.addAttribute("imagen",imagen);
        return "imagenes/subir";
    }

    @PostMapping("/subir")
    public String imatges(@ModelAttribute Imagen imagen, @RequestParam("file") MultipartFile img, String codigoTipoEspacio) throws IOException {


        InputStream is = img.getInputStream();

        int contador = imagenesServicio.numImagenes(codigoTipoEspacio) +1;
        String nombreImagen = codigoTipoEspacio + "-" + contador + ".webp";

        imagen.setNombreImagen(nombreImagen);
        imagen.setCodigoTipoEspacio(codigoTipoEspacio);

        imagenesServicio.guardar(imagen);


        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] readBuf = new byte[4096];
        while (is.available() > 0) {
            int bytesRead = is.read(readBuf);
            os.write(readBuf, 0, bytesRead);
        }


        String fileName = ruta + nombreImagen;

        OutputStream outputStream = new FileOutputStream(fileName);
        os.writeTo(outputStream);

        return "redirect:/imagenes";
    }
}
