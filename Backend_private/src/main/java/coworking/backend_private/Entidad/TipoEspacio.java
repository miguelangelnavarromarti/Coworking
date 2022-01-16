package coworking.backend_private.Entidad;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "TIPOS_ESPACIOS")
public class TipoEspacio {

    @Id
    private String codigo;
    private String nombre;

}
