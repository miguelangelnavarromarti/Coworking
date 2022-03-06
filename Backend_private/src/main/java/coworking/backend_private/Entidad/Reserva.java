package coworking.backend_private.Entidad;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "RESERVAS")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer codigo;
    String localizador;
    @ManyToOne(optional = false)
    @JoinColumn(name="codigoCliente")
    Cliente cliente;
    @ManyToOne(optional = false)
    @JoinColumn(name="hora")
    HorarioDisponible horarioDisponible;
    @ManyToOne(optional = false)
    @JoinColumn(name="codigoEspacio")
    Espacio espacio;
    String estado;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate dia;
    @Column(columnDefinition = "DECIMAL")
    double precio;
    @Column(insertable = false, updatable = false)
    private Timestamp diaHoraCreacion;
}
