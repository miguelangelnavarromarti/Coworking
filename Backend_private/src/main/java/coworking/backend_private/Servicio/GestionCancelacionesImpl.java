package coworking.backend_private.Servicio;

import coworking.backend_private.Entidad.GestionCancelacion;
import coworking.backend_private.Repositorio.GestionCancelacionesRepositorio;
import coworking.backend_private.Servicio.Interficie.IGestionCancelacionesServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GestionCancelacionesImpl implements IGestionCancelacionesServicio {

    @Autowired
    private GestionCancelacionesRepositorio gestionCancelacionesRepositorio;

    @Override
    public List<GestionCancelacion> verTodo() {
        return (List<GestionCancelacion>) gestionCancelacionesRepositorio.findAll();
    }

    @Override
    public void guardar(GestionCancelacion gestionCancelacion) {
        gestionCancelacionesRepositorio.save(gestionCancelacion);
    }

    @Override
    public GestionCancelacion buscarPorCodigo(Integer codigo) {
        return gestionCancelacionesRepositorio.getById(codigo);
    }

    @Override
    public void eliminar(Integer codigo) {
        gestionCancelacionesRepositorio.deleteById(codigo);
    }
}
