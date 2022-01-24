package coworking.backend_private.Servicio.Interficie;

import coworking.backend_private.Entidad.GestionCancelacion;

import java.util.List;

public interface IGestionCancelacionesServicio {

    public List<GestionCancelacion> verTodo();
    public void guardar(GestionCancelacion gestionCancelacion);
    public GestionCancelacion buscarPorCodigo(Integer codigo);
    public void eliminar(Integer codigo);
}
