package coworking.backend_private.Repositorio;

import coworking.backend_private.Entidad.BloqueoEspacioHora;
import coworking.backend_private.Entidad.PrimaryKeysCompuestas.BloqueoEspacioHoraPK;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloqueosEspaciosHorasRepositorio extends CrudRepository<BloqueoEspacioHora, BloqueoEspacioHoraPK> {
}
