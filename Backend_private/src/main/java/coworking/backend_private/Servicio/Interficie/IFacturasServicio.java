package coworking.backend_private.Servicio.Interficie;

import coworking.backend_private.Entidad.Factura;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IFacturasServicio {

    public List<Factura> verTodos();

    public void guardar(Factura factura);

    public Factura buscarPorCodigo(Integer codigo);

    public List<Integer> verFacturasConFacturaCancelada();
}
