package coworking.backend_private.Servicio;

import coworking.backend_private.Entidad.GestionOferta;
import coworking.backend_private.Repositorio.GestionOfertasRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GestionOfertasImpl implements IGestionOfertasServicio{

    @Autowired
    private GestionOfertasRepositorio gestionOfertasRepositorio;

    @Override
    public List<GestionOferta> verTodo() {
        return (List<GestionOferta>) gestionOfertasRepositorio.findAll();
    }

    @Override
    public void guardar(GestionOferta gestionOferta) {
        gestionOfertasRepositorio.save(gestionOferta);
    }

    @Override
    public GestionOferta buscarPorCodigo(Integer codigo) {
        return gestionOfertasRepositorio.getById(codigo);
    }
}
