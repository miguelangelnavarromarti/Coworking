package coworking.backend_private.Servicio;

import coworking.backend_private.Entidad.Espacio;
import coworking.backend_private.Entidad.TipoEspacio;
import coworking.backend_private.Repositorio.EspaciosRepositorio;
import coworking.backend_private.Servicio.Interficie.IEspaciosServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EspaciosServicioImpl implements IEspaciosServicio {

    @Autowired
    private EspaciosRepositorio espaciosRepositorio;

    @Override
    public List<Espacio> listaEspacio() {
        return (List<Espacio>) espaciosRepositorio.findAll();
    }

    @Override
    public void guardar (Espacio espacio) {
        espaciosRepositorio.save(espacio);
    }

    @Override
    public Espacio verEspacio(Integer codigo) {
        return espaciosRepositorio.getEspacioByCodigo(codigo);
    }

    @Override
    public List<Integer> listaCodigos() {
        List<Espacio> listaEspacios = (List<Espacio>) espaciosRepositorio.findAll();
        List<Integer> listaCodigos = new ArrayList<>();

        for (Espacio espacio : listaEspacios) {
            listaCodigos.add(espacio.getCodigo());
        }

        return  listaCodigos;
    }

    @Override
    public List<Espacio> verEspaciosPorTipoEspacio(TipoEspacio tipoEspacio) {
        return espaciosRepositorio.getEspacioByTipoEspacio(tipoEspacio);
    }
}
