package coworking.backend_private.Repositorio;

import coworking.backend_private.Entidad.BloqueoEspacioHora;
import coworking.backend_private.Entidad.Espacio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BloqueosEspaciosHorasRepositorio extends CrudRepository<BloqueoEspacioHora, Integer> {
    BloqueoEspacioHora findByCodigo(Integer codigo);
    List<BloqueoEspacioHora> findByCodigoEspacioAndDiaBloqueo(Espacio espacio, LocalDate diaBloqueo);
}
