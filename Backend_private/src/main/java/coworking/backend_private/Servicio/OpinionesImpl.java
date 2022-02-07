package coworking.backend_private.Servicio;

import coworking.backend_private.Entidad.Opinion;
import coworking.backend_private.Repositorio.OpinionesRepositorio;
import coworking.backend_private.Servicio.Interficie.IOpinionesServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpinionesImpl implements IOpinionesServicio {

    @Autowired
    private OpinionesRepositorio opinionesRepositorio;

    @Override
    public List<Opinion> verTodo() {
        return opinionesRepositorio.findAll();
    }

    @Override
    public void eliminar(Integer codigo) {
        opinionesRepositorio.deleteById(codigo);
    }
}
