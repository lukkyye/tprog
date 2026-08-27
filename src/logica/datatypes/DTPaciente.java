package logica.datatypes;
import java.time.LocalDate;
import java.util.ArrayList;

public record DTPaciente(DTUsuario datos_usuario, LocalDate fechaNac, LocalDate fechaIn, Integer doc, ArrayList<String> antSalud, Integer cantTicketAcum, Integer cantOrdenesAcum) {
	
}
