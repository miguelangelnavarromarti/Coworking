package coworking.backend_private.Servicio.Interficie;

import coworking.backend_private.Entidad.Imagen;

import java.util.List;

public interface IImagenesServicio {

    public List<Imagen> verTodo();
    public void guardar(Imagen imagen);
    public Imagen buscarPorCodigo(String codigo);
    public void eliminar(String codigo);
}
