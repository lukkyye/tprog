package logica.datatypes;
import java.time.LocalDate;

public record DTResConsulta(String nomC, LocalDate fechaRes, int numReserva,	boolean aplicaBenef, float costoR) {

}
