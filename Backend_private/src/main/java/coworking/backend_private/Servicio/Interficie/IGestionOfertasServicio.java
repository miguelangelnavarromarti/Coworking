package coworking.backend_private.Servicio.Interficie;

import coworking.backend_private.Entidad.GestionOferta;
import coworking.backend_private.Entidad.Tarifa;

import java.util.List;

public interface IGestionOfertasServicio {

    public List<GestionOferta> verTodo();
    public void guardar(GestionOferta gestionOferta);
    public GestionOferta buscarPorCodigo(Integer codigo);
    public void eliminar(Integer codigo);

}
