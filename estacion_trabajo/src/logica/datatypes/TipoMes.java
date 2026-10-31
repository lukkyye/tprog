package logica.datatypes;

import jakarta.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum TipoMes {
	Enero,
	Febrero,
	Marzo,
	Abril,
	Mayo,
	Junio,
	Julio,
	Agosto,
	Septiembre,
	Octubre,
	Noviembre,
	Diciembre;
	
	public static TipoMes fromString(String str) {
		return switch (str.toLowerCase()) {
		case "enero" -> TipoMes.Enero;
		case "febrero" -> TipoMes.Febrero;
		case "marzo" -> TipoMes.Marzo;
		case "abril" -> TipoMes.Abril;
		case "mayo" -> TipoMes.Mayo;
		case "junio" -> TipoMes.Junio;
		case "julio" -> TipoMes.Julio;
		case "agosto" -> TipoMes.Agosto;
		case "septiembre" -> TipoMes.Septiembre;
		case "octubre" -> TipoMes.Octubre;
		case "noviembre" -> TipoMes.Noviembre;
		case "diciembre" -> TipoMes.Diciembre;
		default -> null;
		};
	}
}
