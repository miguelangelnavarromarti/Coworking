package coworking.backend_private.Controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Part;
import java.io.*;

@Controller
@RequestMapping("/imagenes")
public class ImagenesControlador {

    @PostMapping("/subir")
    public String imatges(@RequestParam("file") MultipartFile img) throws IOException {

        //Part filePart = request.getPart("file");

        InputStream is = img.getInputStream();
        String nomImatge = img.getOriginalFilename();

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

        return "redirect:imagenes/subir";
    }
}
