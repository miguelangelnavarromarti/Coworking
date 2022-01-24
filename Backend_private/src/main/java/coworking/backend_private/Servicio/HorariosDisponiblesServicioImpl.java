package coworking.backend_private.Servicio;

import coworking.backend_private.Entidad.HorarioDisponible;
import coworking.backend_private.Repositorio.HorariosDisponiblesRepositorio;
import coworking.backend_private.Servicio.Interficie.IHorariosDisponiblesServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HorariosDisponiblesServicioImpl implements IHorariosDisponiblesServicio {

    @Autowired
    HorariosDisponiblesRepositorio horariosDisponiblesRepositorio;

    @Override
    public List<HorarioDisponible> verHorarioDisponible() {
        return (List<HorarioDisponible>) horariosDisponiblesRepositorio.findAll();
    }
}
