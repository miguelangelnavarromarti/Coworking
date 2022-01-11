package coworking.backend_private.Entidad;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "TARIFAS")
public class Tarifas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double precio;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFin;
    private boolean porDefecto = false;


    public Tarifas(int id, double precio, LocalDateTime dataInicio, LocalDateTime dataFin, boolean porDefecto) {
        this.id = id;
        this.precio = precio;
        this.dataInicio = dataInicio;
        this.dataFin = dataFin;
        this.porDefecto = porDefecto;
    }

    public Tarifas(int id) {
        this.id = id;
    }

    public Tarifas() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDateTime getDataFin() {
        return dataFin;
    }

    public void setDataFin(LocalDateTime dataFin) {
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
                "id=" + id +
                ", precio=" + precio +
                ", dataInicio=" + dataInicio +
                ", dataFin=" + dataFin +
                ", porDefecto=" + porDefecto +
                '}';
    }
}
