package com.gateway.delegators;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;

import com.gateway.connectors.UserControllerConnector;
import com.gateway.interfaces.IUserController;
import com.models.logica.datatypes.DTBeneficioAnual;
import com.models.logica.datatypes.DTConsulta;
import com.models.logica.datatypes.DTConsultaDetalle;
import com.models.logica.datatypes.DTMedico;
import com.models.logica.datatypes.DTPaciente;
import com.models.logica.datatypes.DTResConsulta;
import com.models.logica.datatypes.DTResConsultaDetalle;
import com.models.logica.datatypes.DTUsuario;
import com.models.logica.datatypes.TipoMes;

public class UserControllerDelegator implements IUserController {
	private UserControllerConnector ucc;
	
	public UserControllerDelegator() {
		this.ucc=new UserControllerConnector();
	}
	
	@Override
	public boolean ingresarMedico(String nickname, String password, String nombre, String apellido, String correo,
			String nroReg, TipoMes licencia, int porcCost, String link, ArrayList<String> specs) {
		return ucc.ingresarMedico(nickname, password, nombre, apellido, correo, nroReg, licencia, porcCost, link, specs);
	}

	@Override
	public boolean ingresarPaciente(String nickname, String password, String nombre, String apellido, String correo,
			LocalDate fechaNac, LocalDate fechaIn, int doc, ArrayList<String> antSalud) {
		return ucc.ingresarPaciente(nickname, password, nombre, apellido, correo, fechaNac, fechaIn, doc, antSalud);
	}

	@Override
	public boolean login(String nickname, String password) {
		return ucc.login(nickname, password);
	}

	@Override
	public boolean altaEspecialidad(String espNom, int espPorc) {
		return ucc.altaEspecialidad(espNom, espPorc);
	}

	@Override
	public HashSet<DTUsuario> listarUsuarios() {
		return ucc.listarUsuarios();
	}

	@Override
	public DTMedico listarDatosMedico(String nicknameUsu) {
		return ucc.listarDatosMedico(nicknameUsu);
	}

	@Override
	public DTPaciente listarDatosPaciente(String nicknameUsu) {
		return ucc.listarDatosPaciente(nicknameUsu);
	}

	@Override
	public HashSet<DTConsulta> listarConsultasAsociadas(String nicknameUsu) {
		return ucc.listarConsultasAsociadas(nicknameUsu);
	}

	@Override
	public HashSet<DTResConsulta> listarResConsultas(String nicknameUsu) {
		return ucc.listarResConsultas(nicknameUsu);
	}

	@Override
	public HashSet<DTPaciente> listarPacientes() {
		return ucc.listarPacientes();
	}

	@Override
	public HashSet<DTMedico> listarMedicos() {
		return ucc.listarMedicos();
	}

	@Override
	public DTPaciente seleccionarPaciente(String nickname) {
		return ucc.seleccionarPaciente(nickname);
	}

	@Override
	public DTResConsultaDetalle listarDatosResConsulta(String nickname, int numReserva) {
		return ucc.listarDatosResConsulta(nickname, numReserva);
	}

	@Override
	public DTConsultaDetalle listarDatosConsulta(String nomC) {
		return ucc.listarDatosConsulta(nomC);
	}

	@Override
	public void altaBeneficioAnual(String nickname, int anio, int cantOrdenes, int cantTicket) {
		ucc.altaBeneficioAnual(nickname, anio, cantOrdenes, cantTicket);
	}

	@Override
	public HashSet<DTBeneficioAnual> listarBeneficiosAnuales(String nickname) {
		return ucc.listarBeneficiosAnuales(nickname);
	}

	@Override
	public DTBeneficioAnual listarDatosBeneficioAnual(String nickname, int anio) {
		return ucc.listarDatosBeneficioAnual(nickname, anio);
	}

	@Override
	public DTUsuario listarDatosUsuario(String nickname) {
		return ucc.listarDatosUsuario(nickname);
	}

	@Override
	public ArrayList<String> listarEspecialidadesMedico(String nickname) {
		return ucc.listarEspecialidadesMedico(nickname);
	}

	@Override
	public void modificarMedico(String nickname, String nombre, String apellido, String nroReg, TipoMes mesLic,
			int porcCost, String link, ArrayList<String> especialidades) {
		ucc.modificarMedico(nickname, nombre, apellido, nroReg, mesLic, porcCost, link, especialidades);
	}

	@Override
	public void modificarPaciente(String nickname, String nombre, String apellido, LocalDate fechaNac,
			LocalDate fechaIn, int doc, ArrayList<String> antSalud) {
		ucc.modificarPaciente(nickname, nombre, apellido, fechaNac, fechaIn, doc, antSalud);
	}
	
}
