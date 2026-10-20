package com.gateway.delegators;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.gateway.connectors.UserControllerConnector;
import com.gateway.interfaces.IUserController;
import com.models.datatypes.DTBeneficioAnual;
import com.models.datatypes.DTConsulta;
import com.models.datatypes.DTConsultaDetalle;
import com.models.datatypes.DTMedico;
import com.models.datatypes.DTPaciente;
import com.models.datatypes.DTResConsulta;
import com.models.datatypes.DTResConsultaDetalle;
import com.models.datatypes.DTUsuario;
import com.models.datatypes.TipoMes;

public class UserControllerDelegator implements IUserController {
	private UserControllerConnector ucc;
	
	public UserControllerDelegator() {
		this.ucc=new UserControllerConnector();
	}
	
	@Override
	public boolean ingresarMedico(String nickname, String password, String nombre, String apellido, String correo,
			String nroReg, TipoMes licencia, int porcCost, String link, List<String> specs) {
		return ucc.ingresarMedico(nickname, password, nombre, apellido, correo, nroReg, licencia, porcCost, link, specs);
	}

	@Override
	public boolean ingresarPaciente(String nickname, String password, String nombre, String apellido, String correo,
			LocalDate fechaNac, LocalDate fechaIn, int doc, List<String> antSalud) {
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
	public Set<DTUsuario> listarUsuarios() {
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
	public Set<DTConsulta> listarConsultasAsociadas(String nicknameUsu) {
		return ucc.listarConsultasAsociadas(nicknameUsu);
	}

	@Override
	public Set<DTResConsulta> listarResConsultas(String nicknameUsu) {
		return ucc.listarResConsultas(nicknameUsu);
	}

	@Override
	public Set<DTPaciente> listarPacientes() {
		return ucc.listarPacientes();
	}

	@Override
	public Set<DTMedico> listarMedicos() {
		return ucc.listarMedicos();
	}

	@Override
	public DTPaciente seleccionarPaciente(String nickname) {
		return ucc.seleccionarPaciente(nickname);
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
	public Set<DTBeneficioAnual> listarBeneficiosAnuales(String nickname) {
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
	public List<String> listarEspecialidadesMedico(String nickname) {
		return ucc.listarEspecialidadesMedico(nickname);
	}

	@Override
	public void modificarMedico(String nickname, String nombre, String apellido, String nroReg, TipoMes mesLic,
			int porcCost, String link, List<String> especialidades) {
		ucc.modificarMedico(nickname, nombre, apellido, nroReg, mesLic, porcCost, link, especialidades);
	}

	@Override
	public void modificarPaciente(String nickname, String nombre, String apellido, LocalDate fechaNac,
			LocalDate fechaIn, int doc, List<String> antSalud) {
		ucc.modificarPaciente(nickname, nombre, apellido, fechaNac, fechaIn, doc, antSalud);
	}

	@Override
	public DTResConsultaDetalle listarDatosResConsulta(String nomC, String nickname) {
		return ucc.listarDatosResConsulta(nomC, nickname);
	}
	
}
