package coworking.backend_private.Entidad.PrimaryKeysCompuestas;

import java.io.Serializable;
import java.sql.Date;

public class BloqueoEspacioHoraPK implements Serializable {
    private Integer codigoEspacio;
    private Integer hora;
    private Date diaBloqueo;

    public BloqueoEspacioHoraPK() {
    }

    public BloqueoEspacioHoraPK(Integer codigoEspacio, Integer hora, Date diaBloqueo) {
        this.codigoEspacio = codigoEspacio;
        this.hora = hora;
        this.diaBloqueo = diaBloqueo;
    }
}
