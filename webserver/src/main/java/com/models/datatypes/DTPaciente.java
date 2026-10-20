package com.models.datatypes;
import java.time.LocalDate;
import java.util.List;

public record DTPaciente( String nickname, LocalDate fechaNac, int cantOrdenesAcum, int cantTicketAcum, List<String> antSalud, int doc, LocalDate fechaIngreso) {
	
}
