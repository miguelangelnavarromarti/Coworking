package coworking.backend_private.Entidad;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="HORARIOS_DISPONIBLES")
public class HorarioDisponible {

    @Id
    Integer hora;
}
