package coworking.backend_private.Repositorio;

import coworking.backend_private.Entidad.Tarifa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarifasRepositorio  extends CrudRepository<Tarifa, Integer> {
}
