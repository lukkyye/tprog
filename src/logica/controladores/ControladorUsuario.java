package logica.controladores;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import logica.datatypes.*;
import logica.manejadores.ManejadorUsuario;
public class ControladorUsuario {
	
	public boolean ingresarMedico(String nickname, String nombre, String apellido, String correo, int nroReg, TipoMes licencia, int porcCost, String link, String espNom, int espPorc) {
		ManejadorUsuario mu = ManejadorUsuario.get_instance();
		mu.agregar_medico(nickname, nombre, nombre, correo, nroReg, licencia, porcCost, link, espNom, espPorc);
		return true;
	}
	
	public boolean ingresarPaciente(String nickname, String nombre, String apellido, String correo, LocalDate fechaNac, LocalDate fechaIn, int doc, ArrayList<String> antSalud) {
		ManejadorUsuario mu = ManejadorUsuario.get_instance();
		mu.agregar_paciente(nickname, nombre, apellido, correo, fechaNac, fechaIn, doc, antSalud);
		return true;
	}//Falta
	
}
