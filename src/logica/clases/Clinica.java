package logica.clases;
import java.util.HashSet;

import logica.datatypes.DTClinica;
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
	
	public Clinica(String nomClinica, String desc, String direc, float costoTicket, float costoConsulta, String link) {
		this.nombre= nomClinica;
		this.desc = desc;
		this.dir = direc;
		this.cTicket = costoTicket;
		this.cConsulta = costoConsulta;
		this.web = link;
		this.diasconsulta=new HashSet<DiaConsulta>();
	}
	
	public HashSet<DTDiaConsulta> mostrarDiasConsulta(){
		HashSet<DTDiaConsulta> result = new HashSet<DTDiaConsulta>();
		diasconsulta.forEach(
					dc -> result.add(dc.getDTDiaConsulta())
				);
		return result;
	}

	public DTClinica get_dt() {
		return new DTClinica(nombre, desc, dir, web, cTicket, cConsulta);
	}
}
