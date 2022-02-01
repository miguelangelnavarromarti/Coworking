package coworking.backend_private.Repositorio;

import coworking.backend_private.Entidad.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FacturasRepositorio extends JpaRepository<Factura,Integer> {
    @Query(value = "SELECT codigo FROM FACTURAS WHERE codigo in (SELECT codigoFactura FROM FACTURAS_CANCELACIONES)",nativeQuery = true)
    List<Integer> verFacturasConFacturaCancelada();
}
