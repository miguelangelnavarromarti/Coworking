package coworking.backend_private.Entidad;

import coworking.backend_private.Entidad.PrimaryKeysCompuestas.TraduccionTipoEspacioPK;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="TRADUCCIONES_TIPOS_ESPACIOS")
@IdClass(TraduccionTipoEspacioPK.class)
public class TraduccionTipoEspacio {

    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name="codigoTipoEspacio")
    private TipoEspacio codigoTipoEspacio;
    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name="codigoIdioma")
    private Idioma codigoIdioma;
    private String traduccionDescripcion;

    public TraduccionTipoEspacio(){}

    public TraduccionTipoEspacio(TipoEspacio tipoEspacio, Idioma idioma, String traduccionDescripcion) {
        this.codigoTipoEspacio = tipoEspacio;
        this.codigoIdioma = idioma;
        this.traduccionDescripcion = traduccionDescripcion;
    }
}
