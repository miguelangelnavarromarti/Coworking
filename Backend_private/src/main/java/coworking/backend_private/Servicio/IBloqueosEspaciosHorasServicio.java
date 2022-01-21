package coworking.backend_private.Servicio;

import coworking.backend_private.Entidad.BloqueoEspacioHora;

import java.util.List;

public interface IBloqueosEspaciosHorasServicio {
    public List<BloqueoEspacioHora> verBloqueos();
}
