package logica.clases;
import java.time.LocalTime;
import logica.datatypes.DTDiaSemana;
import logica.datatypes.DTConsulta;
import logica.datatypes.DTDiaConsulta;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DiaConsulta {
	private String nomD;
	private float costoD;
	private LocalTime horaIni;
	private DTDiaSemana dia;
	private String descD;
	private Especialidad especialidad;
	private Map<String, Consulta> consultas;
	//modelado con visibilidad directa sobre consultas idk revisar.
	
	
	public DiaConsulta(String _nomD, float _costoD, LocalTime _horaIni, DTDiaSemana _dia, String _descD, Especialidad _especialidad) {
		this.nomD = _nomD;
		this.costoD = _costoD;
		this.horaIni = _horaIni;
		this.dia = _dia;
		this.descD = _descD;
		this.especialidad = _especialidad;
		this.consultas = new HashMap<String, Consulta>();
	}
	
	public Especialidad getEspecialidad() {
		return especialidad;
	}
	
	public Set<DTConsulta> getDtconsultas(){
		Set<DTConsulta> result = new HashSet<DTConsulta>();
		consultas.values()
			.forEach(
					c -> result.add(c.getDataConsulta())
				);
		return result;
	}
	
	public DTDiaConsulta getDTDiaConsulta() {
		return new DTDiaConsulta(nomD, descD, dia, horaIni, costoD);
	}
	
	public void agregarConsulta(Consulta consulta) {
		consultas.put(consulta.getNomC(), consulta);
	}
	
	public DTDiaSemana getDia() {
		return dia;
	}
	
	public String getNom() {
		return nomD;
	}
	
	public float getCostoD() {
		return costoD;
	}
}
