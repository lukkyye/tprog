package com.gateway.connectors;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;

import com.gateway.interfaces.IUserController;
import com.models.controladores.ControladorUsuario;
import com.models.datatypes.DTBeneficioAnual;
import com.models.datatypes.DTConsulta;
import com.models.datatypes.DTConsultaDetalle;
import com.models.datatypes.DTMedico;
import com.models.datatypes.DTPaciente;
import com.models.datatypes.DTResConsulta;
import com.models.datatypes.DTResConsultaDetalle;
import com.models.datatypes.DTUsuario;
import com.models.datatypes.TipoMes;


public class UserControllerConnector implements IUserController {
	private ControladorUsuario cu;
	
	public UserControllerConnector() {
		this.cu=new ControladorUsuario();
	}

	@Override
	public boolean ingresarMedico(String nickname, String password, String nombre, String apellido, String correo,
			String nroReg, TipoMes licencia, int porcCost, String link, ArrayList<String> specs) {
		return cu.ingresarMedico(nickname, password, nombre, apellido, correo, nroReg, licencia, porcCost, link, specs);
	}

	@Override
	public boolean ingresarPaciente(String nickname, String password, String nombre, String apellido, String correo,
			LocalDate fechaNac, LocalDate fechaIn, int doc, ArrayList<String> antSalud) {
		return cu.ingresarPaciente(nickname, password, nombre, apellido, correo, fechaNac, fechaIn, doc, antSalud);
	}

	@Override
	public boolean login(String nickname, String password) {
		return cu.login(nickname, password);
	}

	@Override
	public boolean altaEspecialidad(String espNom, int espPorc) {
		return cu.altaEspecialidad(espNom, espPorc);
	}

	@Override
	public HashSet<DTUsuario> listarUsuarios() {
		return cu.listarUsuarios();
	}

	@Override
	public DTMedico listarDatosMedico(String nicknameUsu) {
		return cu.listarDatosMedico(nicknameUsu);
	}

	@Override
	public DTPaciente listarDatosPaciente(String nicknameUsu) {
		return cu.listarDatosPaciente(nicknameUsu);
	}

	@Override
	public HashSet<DTConsulta> listarConsultasAsociadas(String nicknameUsu) {
		return cu.listarConsultasAsociadas(nicknameUsu);
	}

	@Override
	public HashSet<DTResConsulta> listarResConsultas(String nicknameUsu) {
		return cu.listarResConsultas(nicknameUsu);
	}

	@Override
	public HashSet<DTPaciente> listarPacientes() {
		return cu.listarPacientes();
	}

	@Override
	public HashSet<DTMedico> listarMedicos() {
		return cu.listarMedicos();
	}

	@Override
	public DTPaciente seleccionarPaciente(String nickname) {
		return cu.seleccionarPaciente(nickname);
	}

	@Override
	public DTResConsultaDetalle listarDatosResConsulta(String nickname, int numReserva) {
		return cu.listarDatosResConsulta(nickname, numReserva);
	}

	@Override
	public DTConsultaDetalle listarDatosConsulta(String nomC) {
		return cu.listarDatosConsulta(nomC);
	}

	@Override
	public void altaBeneficioAnual(String nickname, int anio, int cantOrdenes, int cantTicket) {
		cu.altaBeneficioAnual(nickname, anio, cantOrdenes, cantTicket);
	}

	@Override
	public HashSet<DTBeneficioAnual> listarBeneficiosAnuales(String nickname) {
		return cu.listarBeneficiosAnuales(nickname);
	}

	@Override
	public DTBeneficioAnual listarDatosBeneficioAnual(String nickname, int anio) {
		return cu.listarDatosBeneficioAnual(nickname, anio);
	}

	@Override
	public DTUsuario listarDatosUsuario(String nickname) {
		return cu.listarDatosUsuario(nickname);
	}

	@Override
	public ArrayList<String> listarEspecialidadesMedico(String nickname) {
		return cu.listarEspecialidadesMedico(nickname);
	}

	@Override
	public void modificarMedico(String nickname, String nombre, String apellido, String nroReg, TipoMes mesLic,
			int porcCost, String link, ArrayList<String> especialidades) {
		cu.modificarMedico(nickname, nombre, apellido, nroReg, mesLic, porcCost, link, especialidades);
	}

	@Override
	public void modificarPaciente(String nickname, String nombre, String apellido, LocalDate fechaNac,
			LocalDate fechaIn, int doc, ArrayList<String> antSalud) {
		cu.modificarPaciente(nickname, nombre, apellido, fechaNac, fechaIn, doc, antSalud);
	}
}
