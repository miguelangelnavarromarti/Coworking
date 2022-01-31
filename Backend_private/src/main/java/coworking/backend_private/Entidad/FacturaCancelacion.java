package coworking.backend_private.Entidad;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "FACTURAS_CANCELACIONES")
public class FacturaCancelacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @ManyToOne(optional = false)
    @JoinColumn(name="codigoFactura")
    private Factura codigoFactura;

    @ManyToOne(optional = false)
    @JoinColumn(name="codigoCliente")
    private Cliente codigoCliente;

    @Column(columnDefinition = "DECIMAL")
    private double devolucion;

    private Integer diasAntelacionCancelacion;

    private Integer descuentoCancelacion;

    @Column(insertable = false, updatable = false)
    private Timestamp diaHoraCancelacion;

    public FacturaCancelacion(Factura factura, Cliente codigoCliente, double devolucion, Integer diasAntelacionCancelacion, Integer descuentoCancelacion) {
    }

    public FacturaCancelacion() {

    }
}
