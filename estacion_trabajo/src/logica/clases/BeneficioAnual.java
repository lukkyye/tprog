package logica.clases;

import logica.datatypes.DTBeneficioAnual;

public class BeneficioAnual {
	private int anio;
	private int cantOrdenes;
	private int cantTicket;
	
	public BeneficioAnual (int anio, int cantOrdenes, int cantTicket) {
		this.anio = anio;
		this.cantOrdenes = cantOrdenes;
		this.cantTicket = cantTicket;
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
	public void setCantOrdenes(int cantOrdenes) {
		this.cantOrdenes = cantOrdenes;
	}
	public void setCantTicket(int cantTicket) {
		this.cantTicket = cantTicket;
	}
	public DTBeneficioAnual getDataBeneficioAnual() {
		return new DTBeneficioAnual(anio, cantOrdenes, cantTicket);
	}
}
