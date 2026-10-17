package com.models.datatypes;

public record DTBeneficioAnual(int anio, int cantOrdenes, int cantTicket, int ordenesUsadas) {
	public int ordenesDisponibles() {
		return cantOrdenes - ordenesUsadas;
	}
}