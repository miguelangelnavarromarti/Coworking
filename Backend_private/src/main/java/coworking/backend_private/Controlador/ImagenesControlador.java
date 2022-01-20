package coworking.backend_private.Controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Part;
import java.io.*;

@Controller
@RequestMapping("/imagenes")
public class ImagenesControlador {

    @GetMapping("")
    public String ver(){
        return "imagenes/ver";
    }

    @GetMapping("/formulario")
    public String formulario (){
        return "imagenes/subir";
    }

    @PostMapping("/subir")
    public String imatges(@RequestParam("file") MultipartFile img) throws IOException {

        //Part filePart = request.getPart("file");

        InputStream is = img.getInputStream();
        String nomImatge = img.getOriginalFilename();
        //Posar un @PathVariable per agafar es valor de name des formulari
        //String nomImatge = nombre;

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] readBuf = new byte[4096];
        while (is.available() > 0) {
            int bytesRead = is.read(readBuf);
            os.write(readBuf, 0, bytesRead);
        }
        // Passam l'arxiu a dins una carpeta
        String ruta = "E://DAW//imgCoworking/";
        String fileName = ruta + nomImatge;

        OutputStream outputStream = new FileOutputStream(fileName);
        os.writeTo(outputStream);

        return "imagenes/ver";
    }
}
