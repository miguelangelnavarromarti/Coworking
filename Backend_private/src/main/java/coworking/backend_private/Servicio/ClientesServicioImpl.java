package coworking.backend_private.Servicio;

import coworking.backend_private.Entidad.Cliente;
import coworking.backend_private.Repositorio.ClientesRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientesServicioImpl implements IClientesServicio{

    @Autowired
    private ClientesRepositorio clientesRepositorio;

    @Override
    public List<Cliente> verTodos() {
        return (List<Cliente>)clientesRepositorio.findAll();
    }

    @Override
    public void guardar(Cliente cliente) {
        clientesRepositorio.save(cliente);
    }

    @Override
    public Cliente buscarPorCodigo(Integer codigo) {
        return clientesRepositorio.findById(codigo).orElse(null);
    }
}
