package coworking.backend_private.Servicio;

import coworking.backend_private.Entidad.Idioma;
import coworking.backend_private.Entidad.Espacio;
import coworking.backend_private.Entidad.TraduccionEspacio;
import coworking.backend_private.Repositorio.TraduccionesEspaciosRepositorio;
import coworking.backend_private.Servicio.Interficie.ITraduccionesEspaciosServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TraduccionesEspaciosServicioImpl implements ITraduccionesEspaciosServicio {

    @Autowired
    private TraduccionesEspaciosRepositorio traduccionesEspaciosRepositorio;

    @Override
    public TraduccionEspacio verPorIdiomaYEspacio (Idioma idioma, Espacio espacio) {
        return traduccionesEspaciosRepositorio.findTraduccionEspaciosByCodigoIdiomaAndCodigoEspacio(idioma, espacio);
    }

    @Override
    public void guardar(TraduccionEspacio traduccionEspacio) {
        traduccionesEspaciosRepositorio.save(traduccionEspacio);
    }

    @Override
    public List<TraduccionEspacio> verTraduccionesPorCodigoEspacio (Integer codigoEspacio) {
        return traduccionesEspaciosRepositorio.findTraduccionEspaciosByCodigoEspacio(codigoEspacio);
    }
}
