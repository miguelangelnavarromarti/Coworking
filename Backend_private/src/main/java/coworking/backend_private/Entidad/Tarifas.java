package coworking.backend_private.Entidad;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TARIFAS")
public class Tarifas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;
    private double precio;
    @Temporal(TemporalType.DATE)
    private Date dataInicio;
    @Temporal(TemporalType.DATE)
    private Date dataFin;
    private boolean porDefecto = false;


    public Tarifas(int codigo, double precio, Date dataInicio, Date dataFin, boolean porDefecto) {
        this.codigo = codigo;
        this.precio = precio;
        this.dataInicio = dataInicio;
        this.dataFin = dataFin;
        this.porDefecto = porDefecto;
    }

    public Tarifas(int codigo) {
        this.codigo = codigo;
    }

    public Tarifas() {

    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFin() {
        return dataFin;
    }

    public void setDataFin(Date dataFin) {
        this.dataFin = dataFin;
    }

    public boolean isPorDefecto() {
        return porDefecto;
    }

    public void setPorDefecto(boolean porDefecto) {
        this.porDefecto = porDefecto;
    }

    @Override
    public String toString() {
        return "Tarifas{" +
                "id=" + codigo +
                ", precio=" + precio +
                ", dataInicio=" + dataInicio +
                ", dataFin=" + dataFin +
                ", porDefecto=" + porDefecto +
                '}';
    }
}
