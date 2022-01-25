package coworking.backend_private.Servicio;

import coworking.backend_private.Entidad.Factura;
import coworking.backend_private.Repositorio.FacturasRepositorio;
import coworking.backend_private.Servicio.Interficie.IFacturasServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacturasServicioImpl implements IFacturasServicio {

    @Autowired
    private FacturasRepositorio facturasRepositorio;

    @Override
    public List<Factura> verTodos() {
        return (List<Factura>) facturasRepositorio.findAll();
    }

    @Override
    public void guardar(Factura factura) {
        facturasRepositorio.save(factura);
    }

    @Override
    public Factura buscarPorCodigo(Integer codigo) {
        return facturasRepositorio.findById(codigo).orElse(null);
    }
}
