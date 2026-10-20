package logica.datatypes;
import java.time.LocalDate;

public record DTConsulta(String nomC, LocalDate fechaC, int sala, int cantNum, float costoC, EstadoConsulta estado) {

}
