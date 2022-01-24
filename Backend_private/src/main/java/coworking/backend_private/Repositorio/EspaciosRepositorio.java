package coworking.backend_private.Repositorio;

import coworking.backend_private.Entidad.Espacio;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EspaciosRepositorio extends CrudRepository<Espacio, Integer> {
    Espacio getEspacioByCodigo(Integer codigo);
}