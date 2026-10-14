package logica.datatypes;
import java.time.LocalTime;

public record DTDiaConsulta(String nomD, String descD, DTDiaSemana dia, LocalTime horaIni, float costoD) {

}
