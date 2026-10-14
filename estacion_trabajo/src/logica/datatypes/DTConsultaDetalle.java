package logica.datatypes;
import java.time.LocalDate;
import java.util.HashSet;

public record DTConsultaDetalle(String nomC, String medico, LocalDate fechaC, int sala, int cantNum, HashSet<DTResConsulta> reservas, Float costoC, EstadoConsulta estado) {
	
}
