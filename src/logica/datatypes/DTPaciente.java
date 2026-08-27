package logica.datatypes;
import java.time.LocalDate;
import java.util.ArrayList;

public record DTPaciente(DTUsuario datos_usuario, LocalDate fechaNac, LocalDate fechaIn, int doc, ArrayList<String> antSalud, int cantTicketAcum, int cantOrdenesAcum) {
	
}
