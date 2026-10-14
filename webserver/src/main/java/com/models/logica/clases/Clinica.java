package com.models.logica.clases;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.HashSet;

import com.models.logica.datatypes.DTClinica;
import com.models.logica.datatypes.DTDiaConsulta;
import com.models.logica.datatypes.DTDiaSemana;

public class Clinica {
	
	private String nombre;
	private float cConsulta;  
	private float cTicket;
	private String web;
	private String dir;
	private String desc;
	private HashSet<DiaConsulta> diasconsulta;
	
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
	
	public float getCostoConsulta() {
	    return cConsulta;
	}

	public void agregarDiaConsulta(DiaConsulta diaConsulta) {
	    diasconsulta.add(diaConsulta);
	}
	
	
}
