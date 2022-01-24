package coworking.backend_private.Entidad;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "IMAGENES")
public class Imagen {

    @Id
    private String codigoTipoEspacio;
    @Column(nullable = false, length = 64)
    private String nombreImagen;

    @Transient
    public String getNombreImagen() {
        if (nombreImagen == null || codigoTipoEspacio == null) return null;

        return "E://DAW//imgCoworking/" + nombreImagen;
    }

/*
    CREATE TABLE IMAGENES (
    codigoTipoEspacio varchar(3) not null,
    nombreImagen varchar(64) not null,
    PRIMARY KEY (codigoTipoEspacio)
    );
     */
}
