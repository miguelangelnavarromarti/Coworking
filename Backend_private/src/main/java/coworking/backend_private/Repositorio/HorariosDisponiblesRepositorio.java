package coworking.backend_private.Repositorio;

import coworking.backend_private.Entidad.HorarioDisponible;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HorariosDisponiblesRepositorio extends CrudRepository<HorarioDisponible, Integer> {
    HorarioDisponible findByHora(Integer hora);
}
