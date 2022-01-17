package coworking.backend_private.Entidad;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="IDIOMAS")
public class Idioma {

    @Id
    String codigo;
    String descripcion;
}
