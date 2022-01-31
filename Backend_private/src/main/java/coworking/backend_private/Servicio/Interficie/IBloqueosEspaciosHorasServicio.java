package coworking.backend_private.Servicio.Interficie;

import coworking.backend_private.Entidad.BloqueoEspacioHora;
import coworking.backend_private.Entidad.Espacio;

import java.time.LocalDate;
import java.util.List;

public interface IBloqueosEspaciosHorasServicio {
    public List<BloqueoEspacioHora> verBloqueos();
    public void guardar (BloqueoEspacioHora bloqueoEspacioHora);
    public BloqueoEspacioHora buscarPorCodigo(Integer codigo);
    public void eliminarPorCodigo(Integer codigo);
    public List<BloqueoEspacioHora> verPorEspacioYDia(Espacio espacio, LocalDate dia);
}
