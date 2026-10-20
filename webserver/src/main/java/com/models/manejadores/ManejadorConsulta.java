package com.models.manejadores;
import com.models.clases.Consulta;
import java.util.HashMap;
import java.util.Map;

import com.models.excepciones.ConsultaNotFoundException;


public class ManejadorConsulta {
	private static ManejadorConsulta instance;
	private Map<String, Consulta> consultas;
	
	private ManejadorConsulta() {
		consultas = new HashMap<String, Consulta>();
	}
	
	public static ManejadorConsulta getInstance() {
		if (instance==null) {
			instance=new ManejadorConsulta();
		}
		return instance;
		
	}
	
	public Consulta getConsulta(String nombre) {
		if (consultas.containsKey(nombre)) {
			return consultas.get(nombre);
		}
		throw new ConsultaNotFoundException();
	}
	
	public Map<String, Consulta> getConsulta() {
	    return consultas;
	}
	public void agregarConsulta(Consulta consulta) {
	    consultas.put(consulta.getNomC(), consulta);
	}
	
}
