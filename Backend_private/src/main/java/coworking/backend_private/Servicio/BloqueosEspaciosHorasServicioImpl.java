package coworking.backend_private.Servicio;

import coworking.backend_private.Entidad.BloqueoEspacioHora;
import coworking.backend_private.Repositorio.BloqueosEspaciosHorasRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BloqueosEspaciosHorasServicioImpl implements IBloqueosEspaciosHorasServicio{

    @Autowired
    BloqueosEspaciosHorasRepositorio bloqueosEspaciosHorasRepositorio;

    @Override
    public List<BloqueoEspacioHora> verBloqueos() {
        return (List<BloqueoEspacioHora>) bloqueosEspaciosHorasRepositorio.findAll();
    }
}
