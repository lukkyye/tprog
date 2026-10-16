package logica.datatypes;

public enum EstadoConsulta {
	Ingresada,
	Aceptada,
	Rechazada;
	
	public EstadoConsulta toString(String str) {
		return switch (str.toLowerCase()) {
		case "ingresada" -> EstadoConsulta.Ingresada;
		case "aceptada" -> EstadoConsulta.Aceptada;
		case "rechazada" -> EstadoConsulta.Rechazada;
		default -> null;
		};
	}
}
