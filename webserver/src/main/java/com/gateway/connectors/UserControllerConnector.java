package com.gateway.connectors;
import java.util.List;
import java.util.Set;

import com.gateway.interfaces.IUserController;
import com.soap.ArrayList;
import com.soap.DtBeneficioAnual;
import com.soap.DtConsulta;
import com.soap.DtConsultaDetalle;
import com.soap.DtMedico;
import com.soap.DtPaciente;
import com.soap.DtResConsulta;
import com.soap.DtResConsultaDetalle;
import com.soap.DtUsuario;
import com.soap.LocalDate;
import com.soap.Publisher;
import com.soap.PublisherService;
import com.soap.TipoMes;


public class UserControllerConnector implements IUserController {
	private final PublisherService service;
    private final Publisher port;
	
	public UserControllerConnector() {
		service = new PublisherService();
        port = service.getPublisherPort();
	}

	@Override
	public boolean ingresarMedico(String nickname, String password, String nombre, String apellido, String correo,
			String nroReg, TipoMes licencia, int porcCost, String link, List<String> specs) {
		return port.ingresarMedico(nickname, password, nombre, apellido, correo, nroReg, licencia, porcCost, link, (ArrayList) specs);
	}

	@Override
	public boolean ingresarPaciente(String nickname, String password, String nombre, String apellido, String correo,
			LocalDate fechaNac, LocalDate fechaIn, int doc, List<String> antSalud) {
		return port.ingresarPaciente(nickname, password, nombre, apellido, correo, fechaNac, fechaIn, doc, (ArrayList) antSalud);
	}

	@Override
	public boolean login(String nickname, String password) {
		return port.login(nickname, password);
	}

	@Override
	public boolean altaEspecialidad(String espNom, int espPorc) {
		return port.altaEspecialidad(espNom, espPorc);
	}

	@Override
	public Set<DtUsuario> listarUsuarios() {
		return (Set<DtUsuario>) port.listarUsuarios();
	}

	@Override
	public DtMedico listarDatosMedico(String nicknameUsu) {
		return port.listarDatosMedico(nicknameUsu);
	}

	@Override
	public DtPaciente listarDatosPaciente(String nicknameUsu) {
		return port.listarDatosPaciente(nicknameUsu);
	}

	@Override
	public Set<DtConsulta> listarConsultasAsociadas(String nicknameUsu) {
		return (Set<DtConsulta>) port.listarConsultasAsociadas(nicknameUsu);
	}

	@Override
	public Set<DtResConsulta> listarResConsultas(String nicknameUsu) {
		return (Set<DtResConsulta>) port.listarResConsultas(nicknameUsu);
	}

	@Override
	public Set<DtPaciente> listarPacientes() {
		return (Set<DtPaciente>) port.listarPacientes();
	}

	@Override
	public Set<DtMedico> listarMedicos() {
		return (Set<DtMedico>) port.listarMedicos();
	}

	@Override
	public DtPaciente seleccionarPaciente(String nickname) {
		return port.seleccionarPaciente(nickname);
	}

	@Override
	public DtResConsultaDetalle listarDatosResConsulta(String nomC, String nickname) {
		return port.listarDatosResConsulta(nomC, nickname);
	}

	@Override
	public DtConsultaDetalle listarDatosConsulta(String nomC) {
		return port.listarDatosConsulta(nomC);
	}

	@Override
	public void altaBeneficioAnual(String nickname, int anio, int cantOrdenes, int cantTicket) {
		port.altaBeneficioAnual(nickname, anio, cantOrdenes, cantTicket);
	}

	@Override
	public Set<DtBeneficioAnual> listarBeneficiosAnuales(String nickname) {
		return (Set<DtBeneficioAnual>) port.listarBeneficiosAnuales(nickname);
	}

	@Override
	public DtBeneficioAnual listarDatosBeneficioAnual(String nickname, int anio) {
		return port.listarDatosBeneficioAnual(nickname, anio);
	}

	@Override
	public DtUsuario listarDatosUsuario(String nickname) {
		return port.listarDatosUsuario(nickname);
	}

	@Override
	public List<String> listarEspecialidadesMedico(String nickname) {
		return (List<String>) port.listarEspecialidadesMedico(nickname);
	}

	@Override
	public void modificarMedico(String nickname, String nombre, String apellido, String nroReg, TipoMes mesLic,
			int porcCost, String link, List<String> especialidades) {
		port.modificarMedico(nickname, nombre, apellido, nroReg, mesLic, porcCost, link, (ArrayList) especialidades);
	}

	@Override
	public void modificarPaciente(String nickname, String nombre, String apellido, LocalDate fechaNac,
			LocalDate fechaIn, int doc, List<String> antSalud) {
		port.modificarPaciente(nickname, nombre, apellido, fechaNac, fechaIn, doc, (ArrayList) antSalud);
	}
}
