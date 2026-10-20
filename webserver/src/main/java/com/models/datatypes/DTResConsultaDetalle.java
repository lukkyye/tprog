package com.models.datatypes;
import java.time.LocalDate;

public record DTResConsultaDetalle(LocalDate fechaRes, int numReserva, boolean aplicaBenef, String medicoC, String nomC, LocalDate fechaC, int salaC, int cantNumC, float costoC, float costoR) {

}
