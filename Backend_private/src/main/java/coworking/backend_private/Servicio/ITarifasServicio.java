package coworking.backend_private.Servicio;

import coworking.backend_private.Entidad.Tarifas;

import java.util.List;

public interface ITarifasServicio {

    public List<Tarifas> listarTodo();
    public void guardar(Tarifas tarifa);
    public Tarifas buscarPorId(Integer id);

}
