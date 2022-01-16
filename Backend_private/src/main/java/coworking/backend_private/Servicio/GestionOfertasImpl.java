package coworking.backend_private.Servicio;

import coworking.backend_private.Entidad.GestionOferta;
import coworking.backend_private.Entidad.Tarifa;
import coworking.backend_private.Repositorio.GestionOfertasRepositorio;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class GestionOfertasImpl implements IGestionOfertasServicio{

    @Autowired
    private GestionOfertasRepositorio gestionOfertasRepositorio;

    @Override
    public List<Tarifa> verTodo() {
        return null;
    }

    @Override
    public void guardar(GestionOferta gestionOferta) {

    }

    @Override
    public Tarifa buscarPorCodigo(Integer id) {
        return null;
    }
}
