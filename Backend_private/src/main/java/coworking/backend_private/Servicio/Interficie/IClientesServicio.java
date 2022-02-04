package coworking.backend_private.Servicio.Interficie;

import coworking.backend_private.Entidad.Cliente;

import java.util.List;

public interface IClientesServicio {
    public List<Cliente> verTodos();

    public void guardar(Cliente cliente);

    public Cliente buscarPorCodigo(Integer codigo);

    public List<String> listarNombresUsuarios();

    public List<String> listarEmails();

    public Cliente verClientePorNombreUsuario(String nombreUsuario);

    public List<Integer> verCodigos();
}
