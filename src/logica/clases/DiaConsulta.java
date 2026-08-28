package logica.clases;
import java.time.LocalTime;
import logica.datatypes.DTDiaSemana;
import logica.datatypes.DTConsulta;
import logica.datatypes.DTDiaConsulta;
import java.util.HashMap;
import java.util.HashSet;

public class DiaConsulta {
	private String nomD;
	private float costoD;
	private LocalTime horaIni;
	private DTDiaSemana dia;
	private String descD;
	
	private HashMap<String, Consulta> consultas;
	//modelado con visibilidad directa sobre consultas idk revisar.
	
	
	public DiaConsulta(String _nomD, float _costoD, LocalTime _horaIni, DTDiaSemana _dia, String _descD) {
		this.nomD=_nomD;
		this.costoD=_costoD;
		this.horaIni=_horaIni;
		this.dia=_dia;
		this.descD=_descD;
		this.consultas=new HashMap<String, Consulta>();
	}
	
	
	public HashSet<DTConsulta> get_dtconsultas(){
		HashSet<DTConsulta> result = new HashSet<DTConsulta>();
		consultas.values()
			.forEach(
					c -> result.add(c.getDataConsulta())
				);
		return result;
	}
	public DTDiaConsulta getDTDiaConsulta() {
		return new DTDiaConsulta(nomD, descD, dia, horaIni, costoD);
	}
}
