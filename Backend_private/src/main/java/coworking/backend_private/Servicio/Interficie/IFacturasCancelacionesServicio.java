package coworking.backend_private.Servicio.Interficie;

import coworking.backend_private.Entidad.Factura;
import coworking.backend_private.Entidad.FacturaCancelacion;

import java.util.List;

public interface IFacturasCancelacionesServicio {
    public List<FacturaCancelacion> verFacturasCancelaciones();
    public FacturaCancelacion verFacturaCancelacionPorFactura(Factura factura);
    public void guardar(FacturaCancelacion facturaCancelacion);
}
