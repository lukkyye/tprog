package logica.manejadores;
import java.util.HashSet;
import logica.clases.Especialidad;
import logica.excepciones.SpecialityNotFoundException;

public class ManejadorEspecialidad {
	private static ManejadorEspecialidad instance;
	private ManejadorEspecialidad() {}
	
	private HashSet<Especialidad> especialidades;
	
	public static ManejadorEspecialidad get_instance() {
		if (instance==null) {
			return new ManejadorEspecialidad();
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
	
	//creoEsp me parece que devolvia Especialidad si encontraba alguna y si no, la creaba. cualquier cosa es corregible.
	public Especialidad find_or_create(String _espNom, int _espPorc) {
		Especialidad e;
		try {
			e = this.getEspecialidad(_espNom);
		} catch (SpecialityNotFoundException c) {
			e = new Especialidad(_espNom, _espPorc);
			especialidades.add(e);
		}
		return e;
	}//cambios
	
}
