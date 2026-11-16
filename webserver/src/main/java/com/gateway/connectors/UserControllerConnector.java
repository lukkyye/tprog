package com.gateway.connectors;
import java.util.List;

import java.util.Set;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.time.LocalTime;

import com.soap.DtResAsistio;
import com.gateway.interfaces.IUserController;
import com.soap.ArrayList;
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
import com.soap.MedicoSinEspecialidadException_Exception;
import com.soap.NicknameAlreadyExistsException_Exception;
import com.soap.NumeroDeRegistroAlreadyExistsException_Exception;
import com.soap.Publisher;
import com.soap.PublisherService;
import com.soap.ReservaDoesntExistsException_Exception;
import com.soap.SpecialityNotFoundException_Exception;
import com.soap.TipoMes;
import com.soap.UserNotFoundException_Exception;

import com.soap.net.java.dev.jaxb.array.StringArray;

import com.soap.DtValoracionConsulta;


public class UserControllerConnector implements IUserController {
	private final PublisherService service;
    private final Publisher port;
	
	public UserControllerConnector() {
		service = new PublisherService();
        port = service.getPublisherPort();
	}

	@Override
	public boolean ingresarMedico(String nickname, String password, String nombre, String apellido, String correo,
			String nroReg, TipoMes licencia, int porcCost, String link, List<String> specs) throws EmailAlreadyExistsException_Exception, MedicoSinEspecialidadException_Exception, NicknameAlreadyExistsException_Exception, NumeroDeRegistroAlreadyExistsException_Exception, SpecialityNotFoundException_Exception {
		StringArray toPass = new StringArray();
		for (String elem : specs) {
			toPass.getItem().add(elem);
		}
		return port.ingresarMedico(nickname, password, nombre, apellido, correo, nroReg, licencia, porcCost, link, toPass);
	}

	@Override
	public boolean ingresarPaciente(String nickname, String password, String nombre, String apellido, String correo,
			LocalDate fechaNac, LocalDate fechaIn, int doc, List<String> antSalud) throws EmailAlreadyExistsException_Exception, IdAlreadyExistsException_Exception, NicknameAlreadyExistsException_Exception {
		StringArray toPass = new StringArray();
		for (String elem : antSalud) {
			toPass.getItem().add(elem);
		}
		return port.ingresarPaciente(nickname, password, nombre, apellido, correo, fechaNac.toString(), fechaIn.toString(), doc, toPass);
	}

	@Override
	public boolean login(String nickname, String password) {
		return port.login(nickname, password);
	}

	@Override
	public boolean altaEspecialidad(String espNom, int espPorc) throws EspecialidadAlreadyExistsException_Exception {
		return port.altaEspecialidad(espNom, espPorc);
	}

	@Override
	public Set<DtUsuario> listarUsuarios() {
		return port.listarUsuarios().getItem().stream().collect(Collectors.toSet());
	}

	@Override
	public DtMedico listarDatosMedico(String nicknameUsu) throws UserNotFoundException_Exception {
		return port.listarDatosMedico(nicknameUsu);
	}

	@Override
	public DtPaciente listarDatosPaciente(String nicknameUsu) throws UserNotFoundException_Exception {
		return port.listarDatosPaciente(nicknameUsu);
	}

	@Override
	public Set<DtConsulta> listarConsultasAsociadas(String nicknameUsu) throws ConsultaNotFoundException_Exception, UserNotFoundException_Exception {
		return port.listarConsultasAsociadas(nicknameUsu).getItem().stream().collect(Collectors.toSet());
	}

	@Override
	public Set<DtResConsulta> listarResConsultas(String nicknameUsu) throws UserNotFoundException_Exception {
		return port.listarResConsultas(nicknameUsu).getItem().stream().collect(Collectors.toSet());
	}

	@Override
	public Set<DtPaciente> listarPacientes() {
		return port.listarPacientes().getItem().stream().collect(Collectors.toSet());
	}

	@Override
	public Set<DtMedico> listarMedicos() {
		return port.listarMedicos().getItem().stream().collect(Collectors.toSet());
	}

	@Override
	public DtPaciente seleccionarPaciente(String nickname) throws UserNotFoundException_Exception {
		return port.seleccionarPaciente(nickname);
	}

	@Override
	public DtResConsultaDetalle listarDatosResConsulta(String nomC, String nickname) throws ConsultaNotFoundException_Exception, UserNotFoundException_Exception {
		return port.listarDatosResConsulta(nomC, nickname);
	}

	@Override
	public DtConsultaDetalle listarDatosConsulta(String nomC) throws ConsultaNotFoundException_Exception {
		return port.listarDatosConsulta(nomC);
	}

	@Override
	public void altaBeneficioAnual(String nickname, int anio, int cantOrdenes, int cantTicket) throws BeneficioAlreadyExistsException_Exception, UserNotFoundException_Exception {
		port.altaBeneficioAnual(nickname, anio, cantOrdenes, cantTicket);
	}

	@Override
	public Set<DtBeneficioAnual> listarBeneficiosAnuales(String nickname) throws UserNotFoundException_Exception {
		return port.listarBeneficiosAnuales(nickname).getItem().stream().collect(Collectors.toSet());
	}

	@Override
	public DtBeneficioAnual listarDatosBeneficioAnual(String nickname, int anio) throws BeneficioNotFoundException_Exception, UserNotFoundException_Exception {
		return port.listarDatosBeneficioAnual(nickname, anio);
	}

	@Override
	public DtUsuario listarDatosUsuario(String nickname) throws UserNotFoundException_Exception {
		return port.listarDatosUsuario(nickname);
	}

	@Override
	public List<String> listarEspecialidadesMedico(String nickname) throws UserNotFoundException_Exception {
		return port.listarEspecialidadesMedico(nickname).getItem();
	}

	@Override
	public void modificarMedico(String nickname, String nombre, String apellido, String nroReg, TipoMes mesLic,
			int porcCost, String link, List<String> especialidades) throws MedicoSinEspecialidadException_Exception, NumeroDeRegistroAlreadyExistsException_Exception, SpecialityNotFoundException_Exception, UserNotFoundException_Exception {
		port.modificarMedico(nickname, nombre, apellido, nroReg, mesLic, porcCost, link, (ArrayList) especialidades);
	}

	@Override
	public void modificarPaciente(String nickname, String nombre, String apellido, LocalDate fechaNac,
			LocalDate fechaIn, int doc, List<String> antSalud) throws UserNotFoundException_Exception {
		port.modificarPaciente(nickname, nombre, apellido, fechaNac.toString(), fechaIn.toString(), doc, (ArrayList) antSalud);
	}

	@Override
	public DtSolicitudMedicamento solicitarMedicamento(String nicknamePaciente, String nombre, LocalDate indicacion,
			LocalDate entrega, String direccion, boolean usaBenef) throws UserNotFoundException_Exception,
			MedicamentoNotFoundException_Exception, MedicamentoYaSolicitadoException_Exception,
			MedicamentoVencidoException_Exception, FechaEntregaInvalidaException_Exception,
			DatosMedicamentoInvalidosException_Exception, BeneficioSinTicketsException_Exception {
		// TODO Auto-generated method stub
		return port.solicitarMedicamento(nicknamePaciente, nombre, indicacion.toString(), entrega.toString(), direccion, usaBenef);
	}

	@Override
	public List<DtMedicamento> listarMedicamentosRecetados(String nickname) throws UserNotFoundException_Exception {
		return port.listarMedicamentosRecetados(nickname).getItem();
	}

	@Override
	public void recetarMedicamento(String nicknamePaciente, String nomC, String nombre, String posologia)
			throws UserNotFoundException_Exception, ConsultaNotFoundException_Exception,
			ClinicaNotFoundException_Exception, ReservaDoesntExistsException_Exception,
			MedicamentoAlreadyExistsException_Exception, DatosMedicamentoInvalidosException_Exception {
		port.recetarMedicamento(nicknamePaciente, nomC, nombre, posologia);
	}

	@Override
	public void altaDetalleReserva(String nomC, int numRes, String nicknamePac, boolean asistio, String motivo,
			String diagnostico) throws UserNotFoundException_Exception {
		port.altaDetalleReserva(nomC, numRes, nicknamePac, asistio, motivo, diagnostico);
	}

	@Override
	public DtResAsistio getAltaDetalleReserva(String nomC, int numR) throws ConsultaNotFoundException_Exception {
		return port.getAltaDetalleReserva(nomC, numR);
	}
	
	@Override
	public List<DtValoracionConsulta> listarConsultasAsistidas(String nickname)
	        throws UserNotFoundException_Exception, ConsultaNotFoundException_Exception {
	    return port.listarConsultasAsistidas(nickname).getItem();
	}

	@Override
	public DtValoracionConsulta obtenerValoracionConsulta(String nickname, String nombreConsulta)
	        throws UserNotFoundException_Exception, ConsultaNotFoundException_Exception,
	               ReservaDoesntExistsException_Exception {
	    return port.obtenerValoracionConsulta(nickname, nombreConsulta);
	}

	@Override
	public boolean valorarConsulta(String nickname, String nombreConsulta, int puntaje)
	        throws UserNotFoundException_Exception, ConsultaNotFoundException_Exception,
	               ReservaDoesntExistsException_Exception {
	    return port.valorarConsulta(nickname, nombreConsulta, puntaje);
	}
}
