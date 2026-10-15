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
	
	
	public boolean ingresarMedico(String nickname, String password, String nombre, String apellido, String correo, String nroReg, TipoMes licencia, int porcCost, String link, ArrayList<String> specs) {
		ManejadorUsuario mu = ManejadorUsuario.get_instance();
		mu.agregar_medico(nickname, password, nombre, apellido, correo, nroReg, licencia, porcCost, link, specs);
		return true;
	}
	public boolean login(String nickname, String password) {
		return ManejadorUsuario.get_instance().login(nickname, password);
	}
	public boolean ingresarPaciente(String nickname, String password,String nombre, String apellido, String correo, LocalDate fechaNac, LocalDate fechaIn, int doc, ArrayList<String> antSalud) {
		ManejadorUsuario mu = ManejadorUsuario.get_instance();
		mu.agregar_paciente(nickname, password, nombre, apellido, correo, fechaNac, fechaIn, doc, antSalud);
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
	
	public void altaBeneficioAnual(String nickname, int anio, int cantOrdenes, int cantTicket) {
		ManejadorUsuario.get_instance().agregar_beneficio(nickname, anio, cantOrdenes, cantTicket);
	}
	
	public void modificarBeneficioAnual(String nickname, int anio, int cantOrdenes, int cantTicket) {
		ManejadorUsuario.get_instance().modificar_beneficio(nickname, anio, cantOrdenes, cantTicket);
	}
	
	public HashSet<DTBeneficioAnual> listarBeneficiosAnuales(String nickname) {
		return ManejadorUsuario.get_instance().beneficiosAnuales(nickname);
	}
	
	public DTBeneficioAnual listarDatosBeneficioAnual(String nickname, int anio) {
		return ManejadorUsuario.get_instance().datosBeneficio(nickname, anio);
	}
	
	public DTUsuario listarDatosUsuario(String nickname) {
		return ManejadorUsuario.get_instance().datosUsuario(nickname);
	}
	
	public ArrayList<String> listarEspecialidadesMedico(String nickname) {
		return ManejadorUsuario.get_instance().especialidadesMedico(nickname);
	}
	
	public void modificarMedico(String nickname, String nombre, String apellido, String nroReg, TipoMes mesLic, int porcCost, String link, ArrayList<String> especialidades) {
		ManejadorUsuario.get_instance().modificar_medico(nickname, nombre, apellido, nroReg, mesLic, porcCost, link, especialidades);
	}
	
	public void modificarPaciente(String nickname, String nombre, String apellido, LocalDate fechaNac, LocalDate fechaIn, int doc, ArrayList<String> antSalud) {
		ManejadorUsuario.get_instance().modificar_paciente(nickname, nombre, apellido, fechaNac, fechaIn, doc, antSalud);
	}
	
}
