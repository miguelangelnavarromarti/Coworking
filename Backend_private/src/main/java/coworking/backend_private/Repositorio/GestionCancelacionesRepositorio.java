package coworking.backend_private.Repositorio;

import coworking.backend_private.Entidad.GestionCancelacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GestionCancelacionesRepositorio extends JpaRepository<GestionCancelacion, Integer> {
}
