package coworking.backend_private.Repositorio;

import coworking.backend_private.Entidad.Factura;
import coworking.backend_private.Entidad.FacturaCancelacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturasCancelacionesRepositorio extends JpaRepository<FacturaCancelacion, Integer> {
    FacturaCancelacion findByCodigoFactura(Factura factura);
    FacturaCancelacion findAllByCodigo(Integer codigo);
}
