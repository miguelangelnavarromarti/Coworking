package coworking.backend_private.Servicio.Interficie;

import coworking.backend_private.Entidad.Opinion;

import java.util.List;

public interface IOpinionesServicio {

    public List<Opinion> verTodo();
    public void eliminar(Integer codigo);
}
