package logica.clases;
import java.util.HashSet;

import logica.datatypes.DTDiaConsulta;

public class Clinica {
	//atributos
	
	private String nombre;
	private float cConsulta;  //calculable
	private float cTicket;
	private String web;
	private String dir;
	private String desc;
	private HashSet<DiaConsulta> diasconsulta;
	//medicos ??
	//agregaria que medicos trabajen en cierta clinica.
	
	public String getNombre() {
		return this.nombre;
	};
	public HashSet<DTDiaConsulta> mostrarDiasConsulta(){
		HashSet<DTDiaConsulta> result = new HashSet<DTDiaConsulta>();
		diasconsulta.forEach(
					dc -> result.add(dc.getDTDiaConsulta())
				);
		return result;//hola
	}
}
