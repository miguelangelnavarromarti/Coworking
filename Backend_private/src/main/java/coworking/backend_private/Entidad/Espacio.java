package coworking.backend_private.Entidad;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ESPACIOS")
public class Espacio {

    @Id
    private int codigo;
    @ManyToOne(optional = false)
    @JoinColumn(name="codigoTipoEspacio")
    private TipoEspacio tipoEspacio;
    private String nombre;
    private String descripcion;
    private int capacidad;
    private int numeroSillas;
    private int metrosCuadrados;
    private boolean activo;
}
