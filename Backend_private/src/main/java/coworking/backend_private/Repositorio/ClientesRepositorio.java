package coworking.backend_private.Repositorio;

import coworking.backend_private.Entidad.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ClientesRepositorio extends JpaRepository<Clientes, Integer> {

    List<Clientes> findAll();
}
