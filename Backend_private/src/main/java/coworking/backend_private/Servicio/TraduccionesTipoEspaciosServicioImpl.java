package coworking.backend_private.Servicio;

import coworking.backend_private.Entidad.Idioma;
import coworking.backend_private.Entidad.TipoEspacio;
import coworking.backend_private.Entidad.TraduccionTipoEspacio;
import coworking.backend_private.Repositorio.TraduccionesTipoEspaciosRepositorio;
import coworking.backend_private.Servicio.Interficie.ITraduccionesTipoEspaciosServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TraduccionesTipoEspaciosServicioImpl implements ITraduccionesTipoEspaciosServicio {

    @Autowired
    private TraduccionesTipoEspaciosRepositorio traduccionesTipoEspaciosRepositorio;

    @Override
    public TraduccionTipoEspacio verPorIdiomaYTipoEspacio (Idioma idioma, TipoEspacio tipoEspacio) {
        return traduccionesTipoEspaciosRepositorio.findTraduccionTipoEspaciosByCodigoIdiomaAndCodigoTipoEspacio(idioma, tipoEspacio);
    }

    @Override
    public void guardar(TraduccionTipoEspacio traduccionTipoEspacio) {
        traduccionesTipoEspaciosRepositorio.save(traduccionTipoEspacio);
    }

    @Override
    public List<TraduccionTipoEspacio> verTraduccionesPorCodigoTipoEspacio (String codigoTipoEspacio) {
        return traduccionesTipoEspaciosRepositorio.findTraduccionTipoEspaciosByCodigoTipoEspacio(codigoTipoEspacio);
    }
}
