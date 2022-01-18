package coworking.backend_private.Servicio;

import coworking.backend_private.Entidad.Idioma;
import coworking.backend_private.Entidad.TipoEspacio;
import coworking.backend_private.Repositorio.IdiomasRepositorio;
import coworking.backend_private.Repositorio.TipoEspaciosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IdiomasServicioImpl implements IIdiomasServicio{

    @Autowired
    private IdiomasRepositorio idiomasRepositorio;

    @Override
    public List<Idioma> listaIdiomas() {
        return (List<Idioma>) idiomasRepositorio.findAll();
    }

    @Override
    public void guardar(Idioma idioma) {
        idiomasRepositorio.save(idioma);
    }

    @Override
    public List<String> listaCodigos() {
        List<String> listaCodigos = new ArrayList<>();
        List<Idioma> lista = (List<Idioma>) idiomasRepositorio.findAll();

        for (Idioma idioma : lista) {
            listaCodigos.add(idioma.getCodigo());
        }

        return listaCodigos;
    }

    @Override
    public Idioma verIdioma(String codigo) {
        return idiomasRepositorio.getIdiomaByCodigo(codigo);
    }
}
