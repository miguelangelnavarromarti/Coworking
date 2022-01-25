package coworking.backend_private.Entidad;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@Table(name = "TARIFAS")
public class Tarifa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;
    @ManyToOne(optional = false)
    @JoinColumn(name="codigoTipoEspacio")
    private TipoEspacio codigoTipoEspacio;
    @Column(columnDefinition = "DECIMAL")
    private double precio;
    //@Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataInicio;
    //@Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataFin;
    private boolean porDefecto = false;


    public Tarifa(TipoEspacio codigoTipoEspacio, double precio, LocalDate dataInicio, LocalDate dataFin, boolean porDefecto) {
        this.codigoTipoEspacio = codigoTipoEspacio;
        this.precio = precio;
        this.dataInicio = dataInicio;
        this.dataFin = dataFin;
        this.porDefecto = porDefecto;
    }



    public Tarifa() {

    }
}
