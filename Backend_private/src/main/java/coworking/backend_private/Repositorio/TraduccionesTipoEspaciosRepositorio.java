package coworking.backend_private.Repositorio;

import coworking.backend_private.Entidad.Idioma;
import coworking.backend_private.Entidad.TipoEspacio;
import coworking.backend_private.Entidad.TraduccionTipoEspacio;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TraduccionesTipoEspaciosRepositorio extends CrudRepository<TraduccionTipoEspacio, String> {
    TraduccionTipoEspacio findTraduccionTipoEspaciosByCodigoIdiomaAndCodigoTipoEspacio(Idioma codigoIdioma, TipoEspacio codigoTipoEspacio);
    @Query(value = "SELECT * FROM TRADUCCIONES_TIPOS_ESPACIOS WHERE codigoTipoEspacio = ?1", nativeQuery = true)
    List<TraduccionTipoEspacio> findTraduccionTipoEspaciosByCodigoTipoEspacio(String codigoTipoEspacio);
}
