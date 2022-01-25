package coworking.backend_private.Entidad;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "FACTURAS")
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;
    @ManyToOne(optional = false)
    @JoinColumn(name="codigoCliente")
    private Cliente codigoCliente;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate diaFactura;
    @Column(columnDefinition = "DECIMAL")
    private double precioTotal;
    private Integer minimoHoraOferta;
    private Integer descuentoOferta;

}
