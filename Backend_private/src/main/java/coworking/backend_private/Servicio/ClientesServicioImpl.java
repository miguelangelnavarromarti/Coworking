package coworking.backend_private.Servicio;

import coworking.backend_private.Entidad.Cliente;
import coworking.backend_private.Repositorio.ClientesRepositorio;
import coworking.backend_private.Servicio.Interficie.IClientesServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientesServicioImpl implements IClientesServicio {

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

    @Override
    public List<String> listarNombresUsuarios() {
        List<String> listaNombreUsuarios = new ArrayList<>();
        List<Cliente> listaClientes = clientesRepositorio.findAll();

        for(int i = 0; i < listaClientes.size(); i++){
            listaNombreUsuarios.add(listaClientes.get(i).getNombreUsuario());
        }
        return listaNombreUsuarios;
    }

    @Override
    public List<String> listarEmails() {
        List<String> listaEmails = new ArrayList<>();
        List<Cliente> listaClientes = clientesRepositorio.findAll();

        for(int i = 0; i < listaClientes.size(); i++){
            listaEmails.add(listaClientes.get(i).getEmail());
        }
        return listaEmails;
    }
}
