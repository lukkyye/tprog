package com.models.datatypes;

public enum EstadoConsulta {
	Ingresada,
	Aceptada,
	Rechazada;
	
	public static EstadoConsulta fromString(String str) {
		return switch (str.toLowerCase()) {
		case "ingresada" -> EstadoConsulta.Ingresada;
		case "aceptada" -> EstadoConsulta.Aceptada;
		case "rechazada" -> EstadoConsulta.Rechazada;
		default -> null;
		};
	}
}
