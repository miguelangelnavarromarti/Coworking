package coworking.backend_private.Servicio;

import coworking.backend_private.Entidad.Espacio;
import coworking.backend_private.Entidad.TipoEspacio;

import java.util.List;

public interface IEspaciosServicio {
    public List<Espacio> listaEspacio();
    public void guardar(Espacio espacio);
    public Espacio verEspacio(Integer codigo);
    public List<Integer> listaCodigos();
}
