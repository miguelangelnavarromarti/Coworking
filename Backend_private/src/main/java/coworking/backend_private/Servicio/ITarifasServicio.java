package coworking.backend_private.Servicio;

import coworking.backend_private.Entidad.Tarifa;

import java.util.List;

public interface ITarifasServicio {

    public List<Tarifa> listarTodo();
    public void guardar(Tarifa tarifa);
    public Tarifa buscarPorId(Integer id);
    public int comprobar(Tarifa tarifa);
}
