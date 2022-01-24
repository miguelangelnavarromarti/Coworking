package coworking.backend_private.Servicio;

import coworking.backend_private.Entidad.Imagen;
import coworking.backend_private.Repositorio.ImagenesRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImagenesServicioImpl implements IImagenesServicio{

    @Autowired
    private ImagenesRepositorio imagenesRepositorio;

    @Override
    public List<Imagen> verTodo() {
        return (List<Imagen>) imagenesRepositorio.findAll();
    }

    @Override
    public void guardar(Imagen imagen) {
        imagenesRepositorio.save(imagen);
    }

    @Override
    public Imagen buscarPorCodigo(String codigo) {
        return imagenesRepositorio.getById(codigo);
    }

    @Override
    public void eliminar(String codigo) {
        imagenesRepositorio.deleteById(codigo);
    }
}
