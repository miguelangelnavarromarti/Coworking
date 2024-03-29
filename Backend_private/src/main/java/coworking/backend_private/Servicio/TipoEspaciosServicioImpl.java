package coworking.backend_private.Servicio;

import coworking.backend_private.Entidad.TipoEspacio;
import coworking.backend_private.Repositorio.TipoEspaciosRepositorio;
import coworking.backend_private.Servicio.Interficie.ITipoEspaciosServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TipoEspaciosServicioImpl implements ITipoEspaciosServicio {

    @Autowired
    private TipoEspaciosRepositorio tipoEspacioRepositorio;

    @Override
    public List<TipoEspacio> listaTipoEspacio() {
        return (List<TipoEspacio>) tipoEspacioRepositorio.findAll();
    }

    @Override
    public void guardar(TipoEspacio tipoEspacio) {
        tipoEspacioRepositorio.save(tipoEspacio);
    }

    @Override
    public List<String> listaCodigos() {
        List<String> listaCodigos = new ArrayList<>();
        List<TipoEspacio> lista = (List<TipoEspacio>) tipoEspacioRepositorio.findAll();

        for (TipoEspacio tipoEspacio : lista) {
            listaCodigos.add(tipoEspacio.getCodigo());
        }

        return listaCodigos;
    }

    @Override
    public TipoEspacio verTipoEspacio(String codigo) {
        return tipoEspacioRepositorio.getTipoEspacioByCodigo(codigo);
    }
}
