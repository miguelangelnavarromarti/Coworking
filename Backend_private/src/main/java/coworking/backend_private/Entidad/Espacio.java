package coworking.backend_private.Entidad;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ESPACIOS")
public class Espacio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "Integer")
    private Integer codigo;
    @ManyToOne(optional = false)
    @JoinColumn(name="codigoTipoEspacio")
    private TipoEspacio tipoEspacio;
    @Column(unique=true)
    private String nombre;
    private String descripcion;
    @Column(columnDefinition = "Integer")
    private Integer capacidad;
    @Column(columnDefinition = "Integer")
    private Integer numeroSillas;
    @Column(columnDefinition = "Integer")
    private Integer metrosCuadrados;
    private boolean activo = true;
}
