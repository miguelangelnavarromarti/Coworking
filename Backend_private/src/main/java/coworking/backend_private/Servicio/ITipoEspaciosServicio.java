package coworking.backend_private.Servicio;

import coworking.backend_private.Entidad.Cliente;
import coworking.backend_private.Entidad.TipoEspacio;

import java.util.List;

public interface ITipoEspaciosServicio {
    public List<TipoEspacio> listaTipoEspacio();

    public void guardar(TipoEspacio tipoEspacio);

    public List<String> listaCodigos();
}
