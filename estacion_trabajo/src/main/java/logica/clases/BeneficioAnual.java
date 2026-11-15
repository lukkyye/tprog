package logica.clases;

import  logica.datatypes.DtBeneficioAnual;

public class BeneficioAnual {
	private int anio;
	private int cantOrdenes;
	private int cantTicket;
	private int ordenesUsadas;
	private int ticketUsados;
	
	public BeneficioAnual(int anio, int cantOrdenes, int cantTicket) {
		this.anio = anio;
		this.cantOrdenes = cantOrdenes;
		this.cantTicket = cantTicket;
		this.ordenesUsadas = 0;
		this.ticketUsados = 0;
	}
	
	public int getAnio() {
		return anio;
	}
	public int getCantOrdenes() {
		return cantOrdenes;
	}
	public int getCantTicket() {
		return cantTicket;
	}
	public int getOrdenesUsadas() {
		return ordenesUsadas;
	}
	public int getTicketUsados() {
		return ticketUsados;
	}
	public int ordenesDisponibles() {
		return cantOrdenes - ordenesUsadas;
	}
	public int ticketDisponibles() {
		return cantTicket - ticketUsados;
	}
	public boolean tieneOrdenesDisponibles() {
		return ordenesDisponibles() > 0;
	}
	public boolean tieneTicketDisponibles() {
		return ticketDisponibles() > 0;
	}
	public void usarOrden() {
		ordenesUsadas++;
	}
	public void usarTicket() {
		ticketUsados++;
	}
	public DtBeneficioAnual getDataBeneficioAnual() {
		return new DtBeneficioAnual(anio, cantOrdenes, cantTicket, ordenesUsadas, ticketUsados);
	}
}
