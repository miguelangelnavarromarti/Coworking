package coworking.backend_private.Servicio;

import coworking.backend_private.Entidad.Cliente;

import java.util.List;

public interface IClientesServicio {
    public List<Cliente> verTodos();

    public void guardar(Cliente cliente);

    public Cliente buscarPorCodigo(Integer codigo);

    public Cliente darDeBaja(Integer codigo);
}
