package coworking.backend_private.Repositorio;

import coworking.backend_private.Entidad.Tarifa;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TarifasRepositorio  extends CrudRepository<Tarifa, Integer> {

@Query(value = "SELECT COUNT(codigo) FROM TARIFAS WHERE codigoTipoEspacio = ?1 AND (dataFin != null OR (?2 < dataFin AND ?3 > dataInicio))",nativeQuery = true)
int findTarifaByDataInicioAndDataFin(String tipoEspacio, LocalDate newI, LocalDate newF);

}
