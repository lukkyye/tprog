package com.models.logica.manejadores;
import java.util.ArrayList;
import java.util.HashSet;
import com.models.logica.clases.Especialidad;
import com.models.logica.excepciones.SpecialityNotFoundException;
import com.models.logica.excepciones.EspecialidadAlreadyExistsException;

public class ManejadorEspecialidad {
	private static ManejadorEspecialidad instance;
	private ManejadorEspecialidad() {
		especialidades=new HashSet<Especialidad>();
	}
	
	private HashSet<Especialidad> especialidades;
	
	public static ManejadorEspecialidad get_instance() {
		if (instance==null) {
			instance= new ManejadorEspecialidad();
		}
		return instance;
	}
	
	public boolean existeEsp(String espNom) {
		for (Especialidad e: especialidades) {
			if (e.getNombre().equals(espNom)) {
				return true;
			}
		}
		return false;
	}
	
	public Especialidad getEspecialidad(String espNom) {
		for (Especialidad e: especialidades) {
			if (e.getNombre().equals(espNom)) {
				return e;
			}
		}
		throw new SpecialityNotFoundException("No se encontro especialidad con el nombre ingresado");
	}
	public ArrayList<String> get_especialidades(){
		ArrayList<String> result = new ArrayList<String>();
		especialidades.forEach(e -> {result.add(e.getNombre());});
		return result;
	}
	//creoEsp me parece que devolvia Especialidad si encontraba alguna y si no, la creaba. cualquier cosa es corregible.
	
	
	public Especialidad altaEspecialidad(String espNom, int espPorc) {
		if (existeEsp(espNom)) {
			throw new EspecialidadAlreadyExistsException("Ya existe una especialidad con el nombre ingresado");
		}
		Especialidad e = new Especialidad(espNom, espPorc);
		especialidades.add(e);
		return e;
	}
}
