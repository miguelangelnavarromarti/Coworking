package coworking.backend_private.Repositorio;

import coworking.backend_private.Entidad.Espacio;
import coworking.backend_private.Entidad.HorarioDisponible;
import coworking.backend_private.Entidad.Reserva;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservasRespositorio extends CrudRepository<Reserva, Integer> {
    Reserva findByCodigo(Integer codigo);
    List<Reserva> findByEspacioAndDia(Espacio espacio, LocalDate dia);
    Reserva findByEspacioAndDiaAndHorarioDisponible(Espacio espacio, LocalDate dia, HorarioDisponible hora);

    @Query(value = "SELECT codigoReserva FROM FACTURAS_RESERVAS WHERE codigoFactura =?1",nativeQuery = true)
    List<Integer> verReservaFactura(Integer codigo);
}
