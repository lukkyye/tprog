package com.models.datatypes;
import java.time.LocalDate;
import java.util.ArrayList;

public record DTPaciente( String nickname,LocalDate fechaNac, int cantOrdenesAcum, int cantTicketAcum, ArrayList<String> antSalud, int doc, LocalDate fechaIngreso) {
	
}
