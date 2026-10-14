package com.models.logica.datatypes;
import java.time.LocalDate;

public record DTResConsulta(LocalDate fechaRes, int numReserva,	boolean aplicaBenef, float costoR) {

}
