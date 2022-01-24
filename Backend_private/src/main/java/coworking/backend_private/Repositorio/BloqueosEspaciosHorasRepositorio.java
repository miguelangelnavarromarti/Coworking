package coworking.backend_private.Repositorio;

import coworking.backend_private.Entidad.BloqueoEspacioHora;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloqueosEspaciosHorasRepositorio extends CrudRepository<BloqueoEspacioHora, Integer> {
    BloqueoEspacioHora findByCodigo(Integer codigo);
}
