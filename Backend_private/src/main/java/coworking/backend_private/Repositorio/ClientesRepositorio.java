package coworking.backend_private.Repositorio;

import coworking.backend_private.Entidad.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ClientesRepositorio extends JpaRepository<Cliente, Integer> {
    List<Cliente> findAll();
}
