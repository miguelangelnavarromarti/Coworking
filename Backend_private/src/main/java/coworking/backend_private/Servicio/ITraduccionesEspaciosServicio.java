package coworking.backend_private.Servicio;

import coworking.backend_private.Entidad.Idioma;
import coworking.backend_private.Entidad.Espacio;
import coworking.backend_private.Entidad.TraduccionEspacio;

import java.util.List;

public interface ITraduccionesEspaciosServicio {
    public TraduccionEspacio verPorIdiomaYEspacio (Idioma idioma, Espacio espacio);
    public void guardar(TraduccionEspacio traduccionEspacio);
    public List<TraduccionEspacio> verTraduccionesPorCodigoEspacio (Integer codigoEspacio);
}
