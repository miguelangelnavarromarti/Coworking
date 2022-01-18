package coworking.backend_private.Entidad;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

//@Data
@Entity
@Table(name = "TARIFAS")
public class Tarifa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;
    private String codigoTipoEspacio;
    @Column(columnDefinition = "DECIMAL")
    private double precio;
    //@Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataInicio;
    //@Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataFin;
    private boolean porDefecto = false;


    public Tarifa(String codigoTipoEspacio, double precio, LocalDate dataInicio, LocalDate dataFin, boolean porDefecto) {
        this.codigo = codigo;
        this.codigoTipoEspacio = codigoTipoEspacio;
        this.precio = precio;
        this.dataInicio = dataInicio;
        this.dataFin = dataFin;
        this.porDefecto = porDefecto;
    }

    public Tarifa(int codigo) {
        this.codigo = codigo;
    }

    public Tarifa() {

    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getCodigoTipoEspacio() {
        return codigoTipoEspacio;
    }

    public void setCodigoTipoEspacio(String codigoTipoEspacio) {
        this.codigoTipoEspacio = codigoTipoEspacio;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        /*try {
            this.dataInicio = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }*/

        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFin() {
        return dataFin;
    }

    public void setDataFin(LocalDate dataFin) {
        /*try {
            this.dataFin = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }*/
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
