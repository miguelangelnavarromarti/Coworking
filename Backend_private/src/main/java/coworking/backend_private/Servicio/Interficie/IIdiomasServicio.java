package coworking.backend_private.Servicio.Interficie;

import coworking.backend_private.Entidad.Idioma;

import java.util.List;

public interface IIdiomasServicio {
    public List<Idioma> listaIdiomas();
    public void guardar(Idioma idioma);
    public List<String> listaCodigos();
    public Idioma verIdioma(String codigo);
}
