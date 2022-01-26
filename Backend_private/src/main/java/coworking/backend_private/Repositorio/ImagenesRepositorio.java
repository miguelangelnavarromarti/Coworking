package coworking.backend_private.Repositorio;

import coworking.backend_private.Entidad.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface ImagenesRepositorio extends JpaRepository<Imagen, String> {
    @Query(value = "SELECT COUNT(`nombreImagen`) FROM IMAGENES WHERE codigoTipoEspacio = ?1",nativeQuery = true)
    int numImagenes(String tipoEspacio);
}
