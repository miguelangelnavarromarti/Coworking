package coworking.backend_private.Entidad;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "OPINIONES")
public class Opinion {

    @Id
    private Integer codigo;
    @ManyToOne(optional = false)
    @JoinColumn(name="codigoCliente")
    private Cliente codigoCliente;
    @ManyToOne(optional = false)
    @JoinColumn(name="codigoReserva")
    private Reserva codigoReserva;
    private String titulo;
    private String opinion;
    private Integer puntuacion;

}
