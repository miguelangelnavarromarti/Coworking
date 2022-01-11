package coworking.backend_private.Servicio;

import coworking.backend_private.Entidad.Clientes;
import coworking.backend_private.Repositorio.ClientesRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientesServicioImpl implements IClientesServicio{

    @Autowired
    private ClientesRepositorio clientesRepositorio;

    @Override
    public List<Clientes> verTodos() {
        return (List<Clientes>)clientesRepositorio.findAll();
    }

    @Override
    public void guardar(Clientes cliente) {
        clientesRepositorio.save(cliente);
        return;
    }
}
