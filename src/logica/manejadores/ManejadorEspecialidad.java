package logica.manejadores;
import java.util.ArrayList;
import java.util.HashSet;
import logica.clases.Especialidad;
import logica.excepciones.SpecialityNotFoundException;

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
	public void test_add_basics() {
		especialidades.add(new Especialidad("Cardiologo", 2));
		especialidades.add(new Especialidad("Urologo", 2));
		especialidades.add(new Especialidad("Neurologo", 2));
		especialidades.add(new Especialidad("Pediatra", 2));
		especialidades.add(new Especialidad("Ginecologo", 2));
		especialidades.add(new Especialidad("Neumologo", 2));
		especialidades.add(new Especialidad("Traumatologo", 2));
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
	public Especialidad find_or_create(String _espNom, int _espPorc) {
		Especialidad e;
		try {
			e = this.getEspecialidad(_espNom);
		} catch (SpecialityNotFoundException c) {
			e = new Especialidad(_espNom, _espPorc);
			especialidades.add(e);
		}
		return e;
	}
	
}
