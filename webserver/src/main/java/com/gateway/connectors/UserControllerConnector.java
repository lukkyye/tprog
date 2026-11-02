package com.gateway.connectors;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.time.LocalTime;
import com.gateway.interfaces.IUserController;
import com.soap.ArrayList;
import com.soap.BeneficioAlreadyExistsException_Exception;
import com.soap.BeneficioNotFoundException_Exception;
import com.soap.ConsultaNotFoundException_Exception;
import com.soap.DtBeneficioAnual;
import com.soap.DtConsulta;
import com.soap.DtConsultaDetalle;
import com.soap.DtMedico;
import com.soap.DtPaciente;
import com.soap.DtResConsulta;
import com.soap.DtResConsultaDetalle;
import com.soap.DtUsuario;
import com.soap.EmailAlreadyExistsException_Exception;
import com.soap.EspecialidadAlreadyExistsException_Exception;
import com.soap.IdAlreadyExistsException_Exception;
import com.soap.MedicoSinEspecialidadException_Exception;
import com.soap.NicknameAlreadyExistsException_Exception;
import com.soap.NumeroDeRegistroAlreadyExistsException_Exception;
import com.soap.Publisher;
import com.soap.PublisherService;
import com.soap.SpecialityNotFoundException_Exception;
import com.soap.TipoMes;
import com.soap.UserNotFoundException_Exception;


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
		return port.ingresarMedico(nickname, password, nombre, apellido, correo, nroReg, licencia, porcCost, link, (ArrayList) specs);
	}

	@Override
	public boolean ingresarPaciente(String nickname, String password, String nombre, String apellido, String correo,
			LocalDate fechaNac, LocalDate fechaIn, int doc, List<String> antSalud) throws EmailAlreadyExistsException_Exception, IdAlreadyExistsException_Exception, NicknameAlreadyExistsException_Exception {
		return port.ingresarPaciente(nickname, password, nombre, apellido, correo, fechaNac.toString(), fechaIn.toString(), doc, (ArrayList) antSalud);
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
}
