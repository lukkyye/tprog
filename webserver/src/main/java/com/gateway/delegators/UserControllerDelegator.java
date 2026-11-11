package com.gateway.delegators;
import java.time.LocalDate;
import com.soap.MedicoSinEspecialidadException_Exception;
import com.soap.NicknameAlreadyExistsException_Exception;
import com.soap.NumeroDeRegistroAlreadyExistsException_Exception;
import com.soap.ReservaDoesntExistsException_Exception;
import com.soap.SpecialityNotFoundException_Exception;

import java.util.List;
import java.util.Set;
import com.gateway.connectors.UserControllerConnector;
import com.gateway.interfaces.IUserController;
import com.soap.BeneficioAlreadyExistsException_Exception;
import com.soap.BeneficioNotFoundException_Exception;
import com.soap.BeneficioSinTicketsException_Exception;
import com.soap.ClinicaNotFoundException_Exception;
import com.soap.ConsultaNotFoundException_Exception;
import com.soap.DatosMedicamentoInvalidosException_Exception;
import com.soap.DtBeneficioAnual;
import com.soap.DtConsulta;
import com.soap.DtConsultaDetalle;
import com.soap.DtMedicamento;
import com.soap.DtMedico;
import com.soap.DtPaciente;
import com.soap.DtResConsulta;
import com.soap.DtResConsultaDetalle;
import com.soap.DtSolicitudMedicamento;
import com.soap.DtUsuario;
import com.soap.EmailAlreadyExistsException_Exception;
import com.soap.EspecialidadAlreadyExistsException_Exception;
import com.soap.FechaEntregaInvalidaException_Exception;
import com.soap.IdAlreadyExistsException_Exception;
import com.soap.MedicamentoAlreadyExistsException_Exception;
import com.soap.MedicamentoNotFoundException_Exception;
import com.soap.MedicamentoVencidoException_Exception;
import com.soap.MedicamentoYaSolicitadoException_Exception;
import com.soap.TipoMes;
import com.soap.UserNotFoundException_Exception;


public class UserControllerDelegator implements IUserController {
	private UserControllerConnector ucc;
	
	public UserControllerDelegator() {
		this.ucc=new UserControllerConnector();
	}
	
	@Override
	public boolean ingresarMedico(String nickname, String password, String nombre, String apellido, String correo,
			String nroReg, TipoMes licencia, int porcCost, String link, List<String> specs) throws EmailAlreadyExistsException_Exception, MedicoSinEspecialidadException_Exception, NicknameAlreadyExistsException_Exception, NumeroDeRegistroAlreadyExistsException_Exception, SpecialityNotFoundException_Exception {
		return ucc.ingresarMedico(nickname, password, nombre, apellido, correo, nroReg, licencia, porcCost, link, specs);
	}

	@Override
	public boolean ingresarPaciente(String nickname, String password, String nombre, String apellido, String correo,
			LocalDate fechaNac, LocalDate fechaIn, int doc, List<String> antSalud) throws EmailAlreadyExistsException_Exception, IdAlreadyExistsException_Exception, NicknameAlreadyExistsException_Exception {
		return ucc.ingresarPaciente(nickname, password, nombre, apellido, correo, fechaNac, fechaIn, doc, antSalud);
	}

	@Override
	public boolean login(String nickname, String password) {
		return ucc.login(nickname, password);
	}

	@Override
	public boolean altaEspecialidad(String espNom, int espPorc) throws EspecialidadAlreadyExistsException_Exception {
		return ucc.altaEspecialidad(espNom, espPorc);
	}

	@Override
	public Set<DtUsuario> listarUsuarios() {
		return ucc.listarUsuarios();
	}

	@Override
	public DtMedico listarDatosMedico(String nicknameUsu) throws UserNotFoundException_Exception {
		return ucc.listarDatosMedico(nicknameUsu);
	}

	@Override
	public DtPaciente listarDatosPaciente(String nicknameUsu) throws UserNotFoundException_Exception {
		return ucc.listarDatosPaciente(nicknameUsu);
	}

	@Override
	public Set<DtConsulta> listarConsultasAsociadas(String nicknameUsu) throws ConsultaNotFoundException_Exception, UserNotFoundException_Exception {
		return ucc.listarConsultasAsociadas(nicknameUsu);
	}

	@Override
	public Set<DtResConsulta> listarResConsultas(String nicknameUsu) throws UserNotFoundException_Exception {
		return ucc.listarResConsultas(nicknameUsu);
	}

	@Override
	public Set<DtPaciente> listarPacientes() {
		return ucc.listarPacientes();
	}

	@Override
	public Set<DtMedico> listarMedicos() {
		return ucc.listarMedicos();
	}

	@Override
	public DtPaciente seleccionarPaciente(String nickname) throws UserNotFoundException_Exception {
		return ucc.seleccionarPaciente(nickname);
	}

	@Override
	public DtConsultaDetalle listarDatosConsulta(String nomC) throws ConsultaNotFoundException_Exception {
		return ucc.listarDatosConsulta(nomC);
	}

	@Override
	public void altaBeneficioAnual(String nickname, int anio, int cantOrdenes, int cantTicket) throws BeneficioAlreadyExistsException_Exception, UserNotFoundException_Exception {
		ucc.altaBeneficioAnual(nickname, anio, cantOrdenes, cantTicket);
	}

	@Override
	public Set<DtBeneficioAnual> listarBeneficiosAnuales(String nickname) throws UserNotFoundException_Exception {
		return ucc.listarBeneficiosAnuales(nickname);
	}

	@Override
	public DtBeneficioAnual listarDatosBeneficioAnual(String nickname, int anio) throws BeneficioNotFoundException_Exception, UserNotFoundException_Exception {
		return ucc.listarDatosBeneficioAnual(nickname, anio);
	}

	@Override
	public DtUsuario listarDatosUsuario(String nickname) throws UserNotFoundException_Exception {
		return ucc.listarDatosUsuario(nickname);
	}

	@Override
	public List<String> listarEspecialidadesMedico(String nickname) throws UserNotFoundException_Exception {
		return ucc.listarEspecialidadesMedico(nickname);
	}

	@Override
	public void modificarMedico(String nickname, String nombre, String apellido, String nroReg, TipoMes mesLic,
			int porcCost, String link, List<String> especialidades) throws MedicoSinEspecialidadException_Exception, NumeroDeRegistroAlreadyExistsException_Exception, SpecialityNotFoundException_Exception, UserNotFoundException_Exception {
		ucc.modificarMedico(nickname, nombre, apellido, nroReg, mesLic, porcCost, link, especialidades);
	}

	@Override
	public void modificarPaciente(String nickname, String nombre, String apellido, LocalDate fechaNac,
			LocalDate fechaIn, int doc, List<String> antSalud) throws UserNotFoundException_Exception {
		ucc.modificarPaciente(nickname, nombre, apellido, fechaNac, fechaIn, doc, antSalud);
	}

	@Override
	public DtResConsultaDetalle listarDatosResConsulta(String nomC, String nickname) throws ConsultaNotFoundException_Exception, UserNotFoundException_Exception {
		return ucc.listarDatosResConsulta(nomC, nickname);
	}

	@Override
	public DtSolicitudMedicamento solicitarMedicamento(String nicknamePaciente, String nombre, LocalDate indicacion,
			LocalDate entrega, String direccion, boolean usaBenef) throws UserNotFoundException_Exception,
			MedicamentoNotFoundException_Exception, MedicamentoYaSolicitadoException_Exception,
			MedicamentoVencidoException_Exception, FechaEntregaInvalidaException_Exception,
			DatosMedicamentoInvalidosException_Exception, BeneficioSinTicketsException_Exception {
		return ucc.solicitarMedicamento(nicknamePaciente, nombre, indicacion, entrega, direccion, usaBenef);
	}

	@Override
	public List<DtMedicamento> listarMedicamentosRecetados(String nickname) throws UserNotFoundException_Exception {
		return ucc.listarMedicamentosRecetados(nickname);
	}

	@Override
	public void recetarMedicamento(String nicknamePaciente, String nomC, String nombre, String posologia)
			throws UserNotFoundException_Exception, ConsultaNotFoundException_Exception,
			ClinicaNotFoundException_Exception, ReservaDoesntExistsException_Exception,
			MedicamentoAlreadyExistsException_Exception, DatosMedicamentoInvalidosException_Exception {
		ucc.recetarMedicamento(nicknamePaciente, nomC, nombre, posologia);
	}
	
}
