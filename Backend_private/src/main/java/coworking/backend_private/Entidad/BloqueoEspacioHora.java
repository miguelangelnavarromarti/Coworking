package coworking.backend_private.Entidad;

import coworking.backend_private.Entidad.PrimaryKeysCompuestas.BloqueoEspacioHoraPK;
import coworking.backend_private.Entidad.PrimaryKeysCompuestas.TraduccionEspacioPK;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@Entity
@Table(name="BLOQUEOS_ESPACIOS_HORAS")
@IdClass(BloqueoEspacioHoraPK.class)
public class BloqueoEspacioHora {

    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name="codigoEspacio")
    private Espacio codigoEspacio;
    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name="hora")
    private HorarioDisponible hora;
    @Id
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date diaBloqueo;
    private Timestamp diaHoraCreacion;
}
