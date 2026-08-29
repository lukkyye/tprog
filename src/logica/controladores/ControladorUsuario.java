package logica.controladores;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import logica.datatypes.*;
import logica.manejadores.ManejadorEspecialidad;
import logica.manejadores.ManejadorUsuario;
import logica.clases.Especialidad;

public class ControladorUsuario {
	
	public boolean ingresarMedico(String nickname, String nombre, String apellido, String correo, int nroReg, TipoMes licencia, int porcCost, String link, String espNom, int espPorc) {
		ManejadorUsuario mu = ManejadorUsuario.get_instance();
		mu.agregar_medico(nickname, nombre, apellido, correo, nroReg, licencia, porcCost, link, espNom, espPorc);
		return true;
	}
	
	public boolean ingresarPaciente(String nickname, String nombre, String apellido, String correo, LocalDate fechaNac, LocalDate fechaIn, int doc, ArrayList<String> antSalud) {
		ManejadorUsuario mu = ManejadorUsuario.get_instance();
		mu.agregar_paciente(nickname, nombre, apellido, correo, fechaNac, fechaIn, doc, antSalud);
		return true;
	}//Falta
	
	public boolean ingresoEspecialidad(String nickname, String espNom, int espPorc) {
		Especialidad esp = ManejadorEspecialidad.get_instance().find_or_create(espNom, espPorc);
		ManejadorUsuario.get_instance().agregarEspecialidadAMedico(nickname, esp);
		return true;
	}
	
	public HashSet<DTUsuario> listarUsuarios() {
		return ManejadorUsuario.get_instance().get_usuarios();
	}
	
	public DTMedico listarDatosMedico(String nicknameUsu) {
		return ManejadorUsuario.get_instance().datosMedico(nicknameUsu);
	}
	
	public DTPaciente listarDatosPaciente(String nicknameUsu) {
		return ManejadorUsuario.get_instance().datosPaciente(nicknameUsu);
	}
	
	public HashSet<DTConsulta> listarConsultasAsociadas(String nicknameUsu) {
		return ManejadorUsuario.get_instance().consultasAsociadas(nicknameUsu);
	}
	
	public HashSet<DTResConsulta> listarResConsultas(String nicknameUsu) {
		return ManejadorUsuario.get_instance().reservasConsultasAsociadas(nicknameUsu);
	}
	
	public HashSet<DTPaciente> listarPacientes() {
		return ManejadorUsuario.get_instance().get_dtpacientes();
	}
	
	public DTPaciente seleccionarPaciente(String nickname) {
		return ManejadorUsuario.get_instance().datosPaciente(nickname);
	}
	
	public DTResConsultaDetalle listarDatosResConsulta(
	        String nicknameUsu, int numReserva) {

	    return ManejadorUsuario.get_instance()
	            .datosResConsulta(nicknameUsu, numReserva);
	}
}
