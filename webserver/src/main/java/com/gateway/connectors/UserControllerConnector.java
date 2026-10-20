package com.gateway.connectors;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

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
	private ControladorUsuario controladorUsuario;
	
	public UserControllerConnector() {
		this.controladorUsuario=new ControladorUsuario();
	}

	@Override
	public boolean ingresarMedico(String nickname, String password, String nombre, String apellido, String correo,
			String nroReg, TipoMes licencia, int porcCost, String link, List<String> specs) {
		return controladorUsuario.ingresarMedico(nickname, password, nombre, apellido, correo, nroReg, licencia, porcCost, link, specs);
	}

	@Override
	public boolean ingresarPaciente(String nickname, String password, String nombre, String apellido, String correo,
			LocalDate fechaNac, LocalDate fechaIn, int doc, List<String> antSalud) {
		return controladorUsuario.ingresarPaciente(nickname, password, nombre, apellido, correo, fechaNac, fechaIn, doc, antSalud);
	}

	@Override
	public boolean login(String nickname, String password) {
		return controladorUsuario.login(nickname, password);
	}

	@Override
	public boolean altaEspecialidad(String espNom, int espPorc) {
		return controladorUsuario.altaEspecialidad(espNom, espPorc);
	}

	@Override
	public Set<DTUsuario> listarUsuarios() {
		return controladorUsuario.listarUsuarios();
	}

	@Override
	public DTMedico listarDatosMedico(String nicknameUsu) {
		return controladorUsuario.listarDatosMedico(nicknameUsu);
	}

	@Override
	public DTPaciente listarDatosPaciente(String nicknameUsu) {
		return controladorUsuario.listarDatosPaciente(nicknameUsu);
	}

	@Override
	public Set<DTConsulta> listarConsultasAsociadas(String nicknameUsu) {
		return controladorUsuario.listarConsultasAsociadas(nicknameUsu);
	}

	@Override
	public Set<DTResConsulta> listarResConsultas(String nicknameUsu) {
		return controladorUsuario.listarResConsultas(nicknameUsu);
	}

	@Override
	public Set<DTPaciente> listarPacientes() {
		return controladorUsuario.listarPacientes();
	}

	@Override
	public Set<DTMedico> listarMedicos() {
		return controladorUsuario.listarMedicos();
	}

	@Override
	public DTPaciente seleccionarPaciente(String nickname) {
		return controladorUsuario.seleccionarPaciente(nickname);
	}

	@Override
	public DTResConsultaDetalle listarDatosResConsulta(String nomC, String nickname) {
		return controladorUsuario.listarDatosResConsulta(nomC, nickname);
	}

	@Override
	public DTConsultaDetalle listarDatosConsulta(String nomC) {
		return controladorUsuario.listarDatosConsulta(nomC);
	}

	@Override
	public void altaBeneficioAnual(String nickname, int anio, int cantOrdenes, int cantTicket) {
		controladorUsuario.altaBeneficioAnual(nickname, anio, cantOrdenes, cantTicket);
	}

	@Override
	public Set<DTBeneficioAnual> listarBeneficiosAnuales(String nickname) {
		return controladorUsuario.listarBeneficiosAnuales(nickname);
	}

	@Override
	public DTBeneficioAnual listarDatosBeneficioAnual(String nickname, int anio) {
		return controladorUsuario.listarDatosBeneficioAnual(nickname, anio);
	}

	@Override
	public DTUsuario listarDatosUsuario(String nickname) {
		return controladorUsuario.listarDatosUsuario(nickname);
	}

	@Override
	public List<String> listarEspecialidadesMedico(String nickname) {
		return controladorUsuario.listarEspecialidadesMedico(nickname);
	}

	@Override
	public void modificarMedico(String nickname, String nombre, String apellido, String nroReg, TipoMes mesLic,
			int porcCost, String link, List<String> especialidades) {
		controladorUsuario.modificarMedico(nickname, nombre, apellido, nroReg, mesLic, porcCost, link, especialidades);
	}

	@Override
	public void modificarPaciente(String nickname, String nombre, String apellido, LocalDate fechaNac,
			LocalDate fechaIn, int doc, List<String> antSalud) {
		controladorUsuario.modificarPaciente(nickname, nombre, apellido, fechaNac, fechaIn, doc, antSalud);
	}
}
