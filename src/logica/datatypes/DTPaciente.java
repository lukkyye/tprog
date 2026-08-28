package logica.datatypes;
import java.time.LocalDate;
import java.util.ArrayList;

public record DTPaciente(LocalDate fechaNac, int cantOrdenesAcum, int cantTicketAcum, ArrayList<String> antSalud, int doc, LocalDate fechaIngreso) {
	
}
