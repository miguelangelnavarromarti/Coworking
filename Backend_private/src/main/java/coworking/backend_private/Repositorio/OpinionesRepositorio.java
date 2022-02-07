package coworking.backend_private.Repositorio;

import coworking.backend_private.Entidad.Opinion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OpinionesRepositorio extends JpaRepository<Opinion, Integer> {
}
