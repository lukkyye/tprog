package logica.clases;
import java.util.HashSet;
import java.util.Set;

import logica.datatypes.DTClinica;
import logica.datatypes.DTDiaConsulta;

public class Clinica {
	
	private String nombre;
	private float cConsulta;  
	private float cTicket;
	private String web;
	private String dir;
	private String desc;
	private Set<DiaConsulta> diasconsulta;
	
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
	
	public Set<DTDiaConsulta> mostrarDiasConsulta(){
		Set<DTDiaConsulta> result = new HashSet<DTDiaConsulta>();
		diasconsulta.forEach(
					dc -> result.add(dc.getDTDiaConsulta())
				);
		return result;
	}

	public DTClinica getDt() {
		return new DTClinica(nombre, desc, dir, web, cTicket, cConsulta);
	}
	
	
	public float getCostoConsulta() {
	    return cConsulta;
	}

	public void agregarDiaConsulta(DiaConsulta diaConsulta) {
	    diasconsulta.add(diaConsulta);
	}
	
	
}
