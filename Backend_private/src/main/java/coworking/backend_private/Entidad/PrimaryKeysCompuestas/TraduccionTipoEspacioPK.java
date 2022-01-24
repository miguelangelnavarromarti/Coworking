package coworking.backend_private.Entidad.PrimaryKeysCompuestas;

import java.io.Serializable;

public class TraduccionTipoEspacioPK implements Serializable {
    private String codigoTipoEspacio;
    private String codigoIdioma;

    public TraduccionTipoEspacioPK() {

    }

    public TraduccionTipoEspacioPK(String codigoTipoEspacio, String codigoIdioma) {
        this.codigoTipoEspacio = codigoTipoEspacio;
        this.codigoIdioma = codigoIdioma;
    }
}
