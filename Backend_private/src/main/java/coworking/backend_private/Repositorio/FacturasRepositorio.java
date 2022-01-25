package coworking.backend_private.Repositorio;

import coworking.backend_private.Entidad.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturasRepositorio extends JpaRepository<Factura,Integer> {
}
