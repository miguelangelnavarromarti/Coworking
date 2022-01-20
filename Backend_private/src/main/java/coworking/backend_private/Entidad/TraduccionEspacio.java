package coworking.backend_private.Entidad;

import coworking.backend_private.Entidad.PrimaryKeysCompuestas.TraduccionEspacioPK;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="TRADUCCIONES_ESPACIOS")
@IdClass(TraduccionEspacioPK.class)
public class TraduccionEspacio {

    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name="codigoEspacio")
    private Espacio codigoEspacio;
    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name="codigoIdioma")
    private Idioma codigoIdioma;
    private String traduccionNombre;
    private String traduccionDescripcion;

    public TraduccionEspacio(){}

    public TraduccionEspacio(Espacio espacio, Idioma idioma, String traduccionNombre, String traduccionDescripcion) {
        this.codigoEspacio = espacio;
        this.codigoIdioma = idioma;
        this.traduccionNombre = traduccionNombre;
        this.traduccionDescripcion = traduccionDescripcion;
    }
}
