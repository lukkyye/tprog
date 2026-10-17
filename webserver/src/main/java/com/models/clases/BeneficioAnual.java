package com.models.clases;

import com.models.datatypes.DTBeneficioAnual;

public class BeneficioAnual {
	private int anio;
	private int cantOrdenes;
	private int cantTicket;
	private int ordenesUsadas;
	//private int ticketUsados;
	
	public BeneficioAnual (int anio, int cantOrdenes, int cantTicket) {
		this.anio = anio;
		this.cantOrdenes = cantOrdenes;
		this.cantTicket = cantTicket;
		this.ordenesUsadas = 0;
		
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
	public int ordenesDisponibles() {
		return cantOrdenes - ordenesUsadas;
	}
	public boolean tieneOrdenesDisponibles() {
		return ordenesDisponibles() > 0;
	}
	public void usarOrden() {
		ordenesUsadas++;
	}
	public DTBeneficioAnual getDataBeneficioAnual() {
		return new DTBeneficioAnual(anio, cantOrdenes, cantTicket, ordenesUsadas);
	}
}
