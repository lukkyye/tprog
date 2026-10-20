package logica.datatypes;
import java.time.LocalDate;
import java.util.Set;

public record DTConsultaDetalle(String nomC, String medico, LocalDate fechaC, int sala, int cantNum, Set<DTResConsulta> reservas, Float costoC, EstadoConsulta estado) {
	
}
