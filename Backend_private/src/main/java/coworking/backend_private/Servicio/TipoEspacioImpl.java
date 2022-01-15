package coworking.backend_private.Servicio;

import coworking.backend_private.Entidad.TipoEspacio;
import coworking.backend_private.Repositorio.TipoEspacioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoEspacioImpl implements ITipoEspacioServicio {

    @Autowired
    private TipoEspacioRepositorio tipoEspacioRepositorio;

    @Override
    public List<TipoEspacio> listaTipoEspacio() {

        return (List<TipoEspacio>) tipoEspacioRepositorio.findAll();
    }
}
