package com.models.datatypes;
import java.time.LocalTime;

public record DTDiaConsulta(String nomD, String descD, DTDiaSemana dia, LocalTime horaIni, float costoD) {

}
