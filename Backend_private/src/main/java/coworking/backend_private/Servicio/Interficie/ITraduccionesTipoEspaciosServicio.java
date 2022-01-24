package coworking.backend_private.Servicio.Interficie;

import coworking.backend_private.Entidad.Idioma;
import coworking.backend_private.Entidad.TipoEspacio;
import coworking.backend_private.Entidad.TraduccionTipoEspacio;

import java.util.List;

public interface ITraduccionesTipoEspaciosServicio {
    public TraduccionTipoEspacio verPorIdiomaYTipoEspacio (Idioma idioma, TipoEspacio tipoEspacio);
    public void guardar(TraduccionTipoEspacio traduccionTipoEspacio);
    public List<TraduccionTipoEspacio> verTraduccionesPorCodigoTipoEspacio (String codigoTipoEspacio);
}
