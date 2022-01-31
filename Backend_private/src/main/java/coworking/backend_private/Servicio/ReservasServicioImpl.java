package coworking.backend_private.Servicio;

import coworking.backend_private.Entidad.Espacio;
import coworking.backend_private.Entidad.HorarioDisponible;
import coworking.backend_private.Entidad.Reserva;
import coworking.backend_private.Repositorio.ReservasRespositorio;
import coworking.backend_private.Servicio.Interficie.IReservasServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservasServicioImpl implements IReservasServicio {

    @Autowired
    private ReservasRespositorio reservasRespositorio;

    @Override
    public List<Reserva> verReservas() {
        return (List<Reserva>) reservasRespositorio.findAll();
    }

    @Override
    public Reserva verReservaPorCodigo(Integer codigo) {
        return reservasRespositorio.findByCodigo(codigo);
    }

    @Override
    public void guardar(Reserva reserva) {
        reservasRespositorio.save(reserva);
    }

    @Override
    public List<Reserva> verReservasEspacioYDia(Espacio espacio, LocalDate dia) {
        return reservasRespositorio.findByEspacioAndDia(espacio, dia);
    }

    @Override
    public Reserva verReservaEspacioDiaYHora(Espacio espacio, LocalDate dia, HorarioDisponible hora) {
        return reservasRespositorio.findByEspacioAndDiaAndHorarioDisponible(espacio, dia, hora);
    }
}
