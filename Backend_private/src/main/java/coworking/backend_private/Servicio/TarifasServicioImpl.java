package coworking.backend_private.Servicio;

import coworking.backend_private.Entidad.Tarifas;
import coworking.backend_private.Repositorio.TarifasRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarifasServicioImpl implements ITarifasServicio{

    @Autowired
    private TarifasRepositorio tarifasRepositorio;



    @Override
    public List<Tarifas> listarTodo() {
        return (List<Tarifas>) tarifasRepositorio.findAll();
    }

    @Override
    public void guardar(Tarifas tarifa) {
        tarifasRepositorio.save(tarifa);
    }

    @Override
    public Tarifas buscarPorId(Integer id) {
        return tarifasRepositorio.findById(id).orElse(null);
    }
}
