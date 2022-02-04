package coworking.backend_private.Servicio.Interficie;

import coworking.backend_private.Entidad.Cliente;
import coworking.backend_private.Entidad.Espacio;
import coworking.backend_private.Entidad.HorarioDisponible;
import coworking.backend_private.Entidad.Reserva;

import java.time.LocalDate;
import java.util.List;

public interface IReservasServicio {
    public List<Reserva> verReservas();
    public Reserva verReservaPorCodigo(Integer codigo);
    public void guardar(Reserva reserva);
    public List<Reserva> verReservasEspacioYDia(Espacio espacio, LocalDate dia);
    public Reserva verReservaEspacioDiaYHora(Espacio espacio, LocalDate dia, HorarioDisponible hora);
    public List<Reserva> verReservasPorCliente(Cliente cliente);
    public List<Integer> verCodigoReservasPorCodigoFactura(Integer codigoFactura);
}
