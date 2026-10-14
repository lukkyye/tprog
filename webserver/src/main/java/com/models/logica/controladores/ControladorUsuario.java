package com.models.logica.controladores;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import com.models.logica.datatypes.*;
import com.models.logica.manejadores.ManejadorConsulta;
import com.models.logica.manejadores.ManejadorEspecialidad;
import com.models.logica.manejadores.ManejadorUsuario;
import com.models.logica.clases.Consulta;


public class ControladorUsuario {
	
	
	public boolean ingresarMedico(String nickname, String nombre, String apellido, String correo, String nroReg, TipoMes licencia, int porcCost, String link, ArrayList<String> specs) {
		ManejadorUsuario mu = ManejadorUsuario.get_instance();
		mu.agregar_medico(nickname, nombre, apellido, correo, nroReg, licencia, porcCost, link, specs);
		return true;
	}
	
	public boolean ingresarPaciente(String nickname, String nombre, String apellido, String correo, LocalDate fechaNac, LocalDate fechaIn, int doc, ArrayList<String> antSalud) {
		ManejadorUsuario mu = ManejadorUsuario.get_instance();
		mu.agregar_paciente(nickname, nombre, apellido, correo, fechaNac, fechaIn, doc, antSalud);
		return true;
	}//Falta
	
	public boolean altaEspecialidad(String espNom, int espPorc) {
		ManejadorEspecialidad.get_instance().altaEspecialidad(espNom, espPorc);
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
	
	public HashSet<DTMedico> listarMedicos() {
		return ManejadorUsuario.get_instance().get_dtmedicos();
	}
	
	public DTPaciente seleccionarPaciente(String nickname) {
		return ManejadorUsuario.get_instance().datosPaciente(nickname);
	}
	
	public DTResConsultaDetalle listarDatosResConsulta(String nickname, int numReserva) { // Seguir aca
		ManejadorUsuario musu = ManejadorUsuario.get_instance();
		return musu.datosResConsulta(nickname, numReserva);
	}
	
	public DTConsultaDetalle listarDatosConsulta(String nomC) {
	    ManejadorConsulta manejador = ManejadorConsulta.get_instance();

	    Consulta consulta = manejador.get_consulta(nomC);

	    return consulta.getDataConsultaDetalle();
	}

	
}
