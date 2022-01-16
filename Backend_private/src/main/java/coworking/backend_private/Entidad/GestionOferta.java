package coworking.backend_private.Entidad;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="GESTION_OFERTAS")
public class GestionOferta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;
    private int minimoHora;
    private int descuento;

}
