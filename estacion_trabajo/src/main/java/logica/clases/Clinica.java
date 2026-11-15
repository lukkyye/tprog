package logica.clases;
import java.util.HashSet;
import java.util.Set;

import  logica.datatypes.DtClinica;
import  logica.datatypes.DtDiaConsulta;

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
	
	public Set<DtDiaConsulta> mostrarDiasConsulta(){
		Set<DtDiaConsulta> result = new HashSet<DtDiaConsulta>();
		diasconsulta.forEach(
					dc -> result.add(dc.getDTDiaConsulta())
				);
		return result;
	}

	public DtClinica getDt() {
		return new DtClinica(nombre, desc, dir, web, cTicket, cConsulta);
	}
	
	
	public float getCostoConsulta() {
	    return cConsulta;
	}

	public void agregarDiaConsulta(DiaConsulta diaConsulta) {
	    diasconsulta.add(diaConsulta);
	}
	
	public float getCostoTicket() {
		return cTicket;
	}
	
	public boolean tieneDiaConsulta(String nomD) {
		for (DiaConsulta d : diasconsulta) {
			if (d.getNom().equals(nomD)) {
				return true;
			}
		}
		return false;
	}
	
	
}
