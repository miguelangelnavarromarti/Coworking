package coworking.backend_private.Repositorio;

import coworking.backend_private.Entidad.Espacio;
import coworking.backend_private.Entidad.TipoEspacio;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EspaciosRepositorio extends CrudRepository<Espacio, Integer> {
    Espacio getEspacioByCodigo(Integer codigo);
    List<Espacio> getEspacioByTipoEspacio(TipoEspacio espacio);

    @Query(value = "SELECT nombre FROM ESPACIOS",nativeQuery = true)
    List<String> findAllNombres();
}
