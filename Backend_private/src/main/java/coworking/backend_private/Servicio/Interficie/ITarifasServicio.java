package coworking.backend_private.Servicio.Interficie;

import coworking.backend_private.Entidad.Tarifa;
import coworking.backend_private.Entidad.TipoEspacio;

import java.util.List;

public interface ITarifasServicio {

    public List<Tarifa> verTodo();
    public void guardar(Tarifa tarifa);
    public Tarifa buscarPorCodigo(Integer id);
    public int comprobar(Tarifa tarifa);
    public int comprobarConCodigo(Tarifa tarifa);
    public List<Tarifa> verTarifasPorTipoEspacio(TipoEspacio tipoEspacio);
}
