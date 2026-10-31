package logica.datatypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTBeneficioAnual {

    private int anio;
    private int cantOrdenes;
    private int cantTicket;
    private int ordenesUsadas;

    public DTBeneficioAnual() {
    }

    public DTBeneficioAnual(int anio, int cantOrdenes, int cantTicket, int ordenesUsadas) {
        this.anio = anio;
        this.cantOrdenes = cantOrdenes;
        this.cantTicket = cantTicket;
        this.ordenesUsadas = ordenesUsadas;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getCantOrdenes() {
        return cantOrdenes;
    }

    public void setCantOrdenes(int cantOrdenes) {
        this.cantOrdenes = cantOrdenes;
    }

    public int getCantTicket() {
        return cantTicket;
    }

    public void setCantTicket(int cantTicket) {
        this.cantTicket = cantTicket;
    }

    public int getOrdenesUsadas() {
        return ordenesUsadas;
    }

    public void setOrdenesUsadas(int ordenesUsadas) {
        this.ordenesUsadas = ordenesUsadas;
    }

    public int ordenesDisponibles() {
        return cantOrdenes - ordenesUsadas;
    }
}