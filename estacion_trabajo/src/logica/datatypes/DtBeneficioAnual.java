package logica.datatypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtBeneficioAnual {

    private int anio;
    private int cantOrdenes;
    private int cantTicket;
    private int ordenesUsadas;
    private int ticketUsados;

    public DtBeneficioAnual() {
    }

    public DtBeneficioAnual(int anio, int cantOrdenes, int cantTicket, int ordenesUsadas, int ticketUsados) {
        this.anio = anio;
        this.cantOrdenes = cantOrdenes;
        this.cantTicket = cantTicket;
        this.ordenesUsadas = ordenesUsadas;
        this.ticketUsados = ticketUsados;
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
    
    public int getTicketUsados() {
    	return ticketUsados;
    }

    public void setOrdenesUsadas(int ordenesUsadas) {
        this.ordenesUsadas = ordenesUsadas;
    }
    
    public void setTicketUsados(int ticketUsados) {
    	this.ticketUsados = ticketUsados;
    }

    public int ordenesDisponibles() {
        return cantOrdenes - ordenesUsadas;
    }
    
    public int ticketDisponibles() {
    	return cantTicket - ticketUsados;
    }
}