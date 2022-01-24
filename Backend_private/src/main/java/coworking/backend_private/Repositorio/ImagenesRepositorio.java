package coworking.backend_private.Repositorio;

import coworking.backend_private.Entidad.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagenesRepositorio extends JpaRepository<Imagen, String> {
}
