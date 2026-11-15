package logica.manejadores;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import  logica.clases.Especialidad;
import  logica.excepciones.SpecialityNotFoundException;
import  logica.excepciones.EspecialidadAlreadyExistsException;

public class ManejadorEspecialidad {
	private static ManejadorEspecialidad instance;
	private ManejadorEspecialidad() {
		especialidades=new HashSet<Especialidad>();
	}
	
	private Set<Especialidad> especialidades;
	
	public static ManejadorEspecialidad getInstance() {
		if (instance==null) {
			instance= new ManejadorEspecialidad();
			//instance.altaEspecialidad("Medicina General 1", 0);
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
	
	public Especialidad getEspecialidad(String espNom) throws SpecialityNotFoundException{
		for (Especialidad e: especialidades) {
			if (e.getNombre().equals(espNom)) {
				return e;
			}
		}
		throw new SpecialityNotFoundException("No se encontro especialidad con el nombre ingresado");
	}
	public List<String> getEspecialidades(){
		List<String> result = new ArrayList<String>();
		especialidades.forEach(e -> {result.add(e.getNombre()); });
		return result;
	}
	//creoEsp me parece que devolvia Especialidad si encontraba alguna y si no, la creaba. cualquier cosa es corregible.
	
	
	public Especialidad altaEspecialidad(String espNom, int espPorc) throws EspecialidadAlreadyExistsException {
		if (existeEsp(espNom)) {
			throw new EspecialidadAlreadyExistsException("Ya existe una especialidad con el nombre ingresado");
		}
		Especialidad especialidad = new Especialidad(espNom, espPorc);
		especialidades.add(especialidad);
		return especialidad;
	}
}
