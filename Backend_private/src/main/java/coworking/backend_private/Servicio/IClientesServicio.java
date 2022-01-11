package coworking.backend_private.Servicio;

import coworking.backend_private.Entidad.Clientes;

import java.util.List;

public interface IClientesServicio {
    public List<Clientes> verTodos();

    public void guardar(Clientes cliente);
}
