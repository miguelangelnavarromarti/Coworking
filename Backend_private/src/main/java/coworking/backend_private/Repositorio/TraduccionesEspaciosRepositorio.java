package coworking.backend_private.Repositorio;

import coworking.backend_private.Entidad.Idioma;
import coworking.backend_private.Entidad.Espacio;
import coworking.backend_private.Entidad.TraduccionEspacio;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TraduccionesEspaciosRepositorio extends CrudRepository<TraduccionEspacio, String> {
    TraduccionEspacio findTraduccionEspaciosByCodigoIdiomaAndCodigoEspacio(Idioma codigoIdioma, Espacio codigoEspacio);
    @Query(value = "SELECT * FROM TRADUCCIONES_ESPACIOS WHERE codigoEspacio = ?1", nativeQuery = true)
    List<TraduccionEspacio> findTraduccionEspaciosByCodigoEspacio(Integer codigoEspacio);
}
