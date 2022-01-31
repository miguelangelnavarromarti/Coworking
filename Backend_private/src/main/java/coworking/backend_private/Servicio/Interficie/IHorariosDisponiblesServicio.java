package coworking.backend_private.Servicio.Interficie;

import coworking.backend_private.Entidad.HorarioDisponible;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface IHorariosDisponiblesServicio {
    public List<HorarioDisponible> verHorarioDisponible();
    public HorarioDisponible verHora(Integer hora);
}
