package coworking.backend_private.Entidad;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

@Data
@Entity
@Table(name="BLOQUEOS_ESPACIOS_HORAS")
public class BloqueoEspacioHora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @ManyToOne(optional = false)
    @JoinColumn(name="codigoEspacio")
    private Espacio codigoEspacio;
    @ManyToOne(optional = false)
    @JoinColumn(name="hora")
    private HorarioDisponible hora;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate diaBloqueo;
    @Column(insertable = false, updatable = false)
    private Timestamp diaHoraCreacion;
}
