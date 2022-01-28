package coworking.backend_private.Repositorio;

import coworking.backend_private.Entidad.Espacio;
import coworking.backend_private.Entidad.Reserva;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservasRespositorio extends CrudRepository<Reserva, Integer> {
    Reserva findByCodigo(Integer codigo);
    List<Reserva> findByEspacioAndDia(Espacio espacio, LocalDate dia);
}
