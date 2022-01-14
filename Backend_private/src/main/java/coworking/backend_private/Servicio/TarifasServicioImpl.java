package coworking.backend_private.Servicio;

import coworking.backend_private.Entidad.Tarifa;
import coworking.backend_private.Repositorio.TarifasRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarifasServicioImpl implements ITarifasServicio{

    @Autowired
    private TarifasRepositorio tarifasRepositorio;



    @Override
    public List<Tarifa> listarTodo() {
        return (List<Tarifa>) tarifasRepositorio.findAll();
    }

    @Override
    public void guardar(Tarifa tarifa) {

        tarifasRepositorio.save(tarifa);
    }

    @Override
    public Tarifa buscarPorId(Integer id) {
        return tarifasRepositorio.findById(id).orElse(null);
    }

    @Override
    public int comprobar(Tarifa tarifa) {
        return tarifasRepositorio.findTarifaByDataInicioAndDataFin(tarifa.getCodigoTipoEspacio(),tarifa.getDataInicio(), tarifa.getDataFin());
    }


}
