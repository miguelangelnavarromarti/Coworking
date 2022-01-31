package coworking.backend_private.Repositorio;

import coworking.backend_private.Entidad.GestionCancelacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GestionCancelacionesRepositorio extends JpaRepository<GestionCancelacion, Integer> {
    @Query(value = "SELECT * FROM GESTION_CANCELACIONES ORDER BY diasAntelacion ASC;",nativeQuery = true)
    List<GestionCancelacion> ordenaPorDiasAntelacionAsc();
}
