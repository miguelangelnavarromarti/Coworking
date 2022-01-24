package coworking.backend_private.Entidad;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="GESTION_CANCELACIONES")
public class GestionCancelacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;
    private int diasAntelacion;
    private int devolucion;


}
