package com.gateway.delegators;



import java.util.List;
import java.util.Set;
import com.gateway.connectors.ClinicControllerConnector;
import com.gateway.interfaces.IClinicController;
import com.soap.BeneficioSinOrdenesException_Exception;
import com.soap.ConsultaNombreAlreadyExistsException_Exception;
import com.soap.ConsultaNotFoundException_Exception;
import com.soap.DiaConsultaAlreadyExistsException_Exception;
import com.soap.DtClinica;
import com.soap.DtConsulta;
import com.soap.DtConsultaDetalle;
import com.soap.DtDiaConsulta;
import com.soap.DtDiaSemana;
import com.soap.DtPaciente;
import com.soap.DtResConsulta;
import com.soap.DtResConsultaDetalle;
import com.soap.EstadoConsulta;
import com.soap.FechaDiaSemanaInvalidaException_Exception;
import java.time.LocalDate;
import java.time.LocalTime;
import com.soap.MedicoSinEspecialidadException_Exception;
import com.soap.NoNumberAvailableException_Exception;
import com.soap.NombreClinicaAlreadyExistsException_Exception;
import com.soap.ReservaAlreadyExistsException_Exception;
import com.soap.SpecialityNotFoundException_Exception;
import com.soap.UserNotFoundException_Exception;

public class ClinicControllerDelegator implements IClinicController {
	private ClinicControllerConnector controladorClinica;
	
	public ClinicControllerDelegator() {
		this.controladorClinica=new ClinicControllerConnector();
	}
	
	@Override
	public Set<DtDiaConsulta> listarDatosDiaConsulta() {
		return controladorClinica.listarDatosDiaConsulta();
	}

	@Override
	public boolean ingresarClinica(String nombre, String desc, String dir, float costoT, float costoC, String link) throws NombreClinicaAlreadyExistsException_Exception {
		return controladorClinica.ingresarClinica(nombre, desc, dir, costoT, costoC, link);
	}
	@Override
	public boolean ingresarClinica(String nombre, String desc, String dir, float costoT, float costoC, String link, String img) throws NombreClinicaAlreadyExistsException_Exception {
		return controladorClinica.ingresarClinica(nombre, desc, dir, costoT, costoC, link, img);
	}

	@Override
	public DtConsultaDetalle listarDatosConsulta(String nomC) throws ConsultaNotFoundException_Exception {
		return controladorClinica.listarDatosConsulta(nomC);
	}
	@Override
	public void setEstadoConsulta(EstadoConsulta state, String nombreConsulta) throws ConsultaNotFoundException_Exception {
		controladorClinica.setEstadoConsulta(state, nombreConsulta);
	}
	@Override
	public DtResConsultaDetalle listarDatosResConsultaCC(String nomC, int numReserva) throws ConsultaNotFoundException_Exception {
		return controladorClinica.listarDatosResConsultaCC(nomC, numReserva);
	}

	@Override
	public Set<DtResConsulta> listarReservasConsulta(String nomC) throws ConsultaNotFoundException_Exception {
		return controladorClinica.listarReservasConsulta(nomC);
	}
	
	@Override
	public Set<DtConsulta> selecListaConsultaDelDia(String nomD) {
		return controladorClinica.selecListaConsultaDelDia(nomD);
	}

	

	@Override
	public Set<DtPaciente> listarPacientes() {
		return controladorClinica.listarPacientes();
	}

	@Override
	public void reservarConsulta(String nicknamePaciente, String nomC, LocalDate fechaReserva, boolean aplicaBenef) throws BeneficioSinOrdenesException_Exception, ConsultaNotFoundException_Exception, NoNumberAvailableException_Exception, ReservaAlreadyExistsException_Exception, UserNotFoundException_Exception {
		controladorClinica.reservarConsulta(nicknamePaciente, nomC, fechaReserva, aplicaBenef);
	}

	@Override
	public Set<DtConsulta> listarConsultas() {
		return controladorClinica.listarConsultas();
	}

	@Override
	public void ingresarConsulta(String nomD, String med, String nomC, LocalDate fecha, int sala, int cantNum) throws ConsultaNombreAlreadyExistsException_Exception, FechaDiaSemanaInvalidaException_Exception, MedicoSinEspecialidadException_Exception, UserNotFoundException_Exception {
		controladorClinica.ingresarConsulta(nomD, med, nomC, fecha, sala, cantNum);
	}

	@Override
	public Set<String> listarClinicas() {
		return controladorClinica.listarClinicas();
	}

	@Override
	public Set<String> listarEspecialidades() {
		return controladorClinica.listarEspecialidades();
	}

	@Override
	public void ingresarDiaConsulta(String nombre, String descripcion, DtDiaSemana dia, LocalTime horaInicio,
			String nombreEspecialidad, String nombreClinica) throws DiaConsultaAlreadyExistsException_Exception, SpecialityNotFoundException_Exception {
		controladorClinica.ingresarDiaConsulta(nombre, descripcion, dia, horaInicio, nombreEspecialidad, nombreClinica);
	}

	@Override
	public List<DtDiaConsulta> listarDiaConsultas() {
		return controladorClinica.listarDiaConsultas();
	}

	@Override
	public List<String> getConsultasPorEspecialidad(String especialidad) {
		return controladorClinica.getConsultasPorEspecialidad(especialidad);
	}

	@Override
	public DtConsulta seleccionarConsulta(String nomC) throws ConsultaNotFoundException_Exception {
		return controladorClinica.seleccionarConsulta(nomC);
	}

	@Override
	public DtClinica seleccionarClinica(String nombre) {
		return controladorClinica.seleccionarClinica(nombre);
	}
}
