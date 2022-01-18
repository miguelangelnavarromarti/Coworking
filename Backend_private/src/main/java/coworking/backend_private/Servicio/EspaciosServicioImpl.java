package coworking.backend_private.Servicio;

import coworking.backend_private.Entidad.Espacio;
import coworking.backend_private.Repositorio.EspaciosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EspaciosServicioImpl implements IEspaciosServicio{

    @Autowired
    private EspaciosRepositorio espaciosRepositorio;

    @Override
    public List<Espacio> listaEspacio() {
        return (List<Espacio>) espaciosRepositorio.findAll();
    }
}
