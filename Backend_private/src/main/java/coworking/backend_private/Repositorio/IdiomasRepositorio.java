package coworking.backend_private.Repositorio;

import coworking.backend_private.Entidad.Idioma;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;

@Repository
public interface IdiomasRepositorio extends CrudRepository<Idioma, String> {
    Idioma getIdiomaByCodigo(String codigo);
}
