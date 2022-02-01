package coworking.backend_private.Servicio;

import coworking.backend_private.Entidad.Factura;
import coworking.backend_private.Entidad.FacturaCancelacion;
import coworking.backend_private.Repositorio.FacturasCancelacionesRepositorio;
import coworking.backend_private.Servicio.Interficie.IFacturasCancelacionesServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacturasCancelacionesServicioImpl implements IFacturasCancelacionesServicio {

    @Autowired
    private FacturasCancelacionesRepositorio facturasCancelacionesRepositorio;

    @Override
    public List<FacturaCancelacion> verFacturasCancelaciones() {
        return facturasCancelacionesRepositorio.findAll();
    }

    @Override
    public FacturaCancelacion verFacturaCancelacionPorFactura(Factura factura) {
        return facturasCancelacionesRepositorio.findByCodigoFactura(factura);
    }

    @Override
    public void guardar(FacturaCancelacion facturaCancelacion) {
        facturasCancelacionesRepositorio.save(facturaCancelacion);
    }

    @Override
    public FacturaCancelacion verFacturaCanceladaPorCodigo(Integer codigo) {
        return facturasCancelacionesRepositorio.findAllByCodigo(codigo);
    }
}
