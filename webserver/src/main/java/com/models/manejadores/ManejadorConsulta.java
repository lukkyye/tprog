package com.models.manejadores;
import com.models.clases.Consulta;
import com.models.clases.Paciente;
import com.models.clases.Usuario;
import com.models.datatypes.DTResConsultaDetalle;

import java.util.HashMap;
import com.models.excepciones.ConsultaNotFoundException;
import com.models.excepciones.UserNotFoundException;

public class ManejadorConsulta {
	private static ManejadorConsulta instance;
	private HashMap<String, Consulta> consultas;
	
	private ManejadorConsulta() {
		consultas = new HashMap<String, Consulta>();
	}
	
	public static ManejadorConsulta get_instance() {
		if (instance==null) {
			instance=new ManejadorConsulta();
		}
		return instance;
		
	}
	
	public Consulta get_consulta(String nombre) {
		if (consultas.containsKey(nombre)) {
			return consultas.get(nombre);
		}
		throw new ConsultaNotFoundException();
	}
	
	public HashMap<String, Consulta> get_consultas() {
	    return consultas;
	}
	public void agregarConsulta(Consulta c) {
	    consultas.put(c.getNomC(), c);
	}
	
}
