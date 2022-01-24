package coworking.backend_private.Entidad.PrimaryKeysCompuestas;

import java.io.Serializable;

public class TraduccionEspacioPK implements Serializable {
    private Integer codigoEspacio;
    private String codigoIdioma;

    public TraduccionEspacioPK() {

    }

    public TraduccionEspacioPK(Integer codigoEspacio, String codigoIdioma) {
        this.codigoEspacio = codigoEspacio;
        this.codigoIdioma = codigoIdioma;
    }
}
