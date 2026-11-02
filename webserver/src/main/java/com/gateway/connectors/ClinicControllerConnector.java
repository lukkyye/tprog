package com.gateway.connectors;

import java.util.Set;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
import com.soap.DtResConsultaDetalle;
import com.soap.EstadoConsulta;
import com.soap.FechaDiaSemanaInvalidaException_Exception;
import java.util.HashSet;
import java.time.LocalDate;
import java.time.LocalTime;
import com.soap.MedicoSinEspecialidadException_Exception;
import com.soap.NoNumberAvailableException_Exception;
import com.soap.NombreClinicaAlreadyExistsException_Exception;
import com.soap.Publisher;
import com.soap.PublisherService;
import com.soap.ReservaAlreadyExistsException_Exception;
import com.soap.SpecialityNotFoundException_Exception;
import com.soap.UserNotFoundException_Exception;
import com.soap.DtResConsulta;

public class ClinicControllerConnector implements IClinicController {	
	private final PublisherService service;
    private final Publisher port;
	
	public ClinicControllerConnector() {
		service = new PublisherService();
        port = service.getPublisherPort();
	}

	@Override
	public Set<DtDiaConsulta> listarDatosDiaConsulta() {
		return (Set<DtDiaConsulta>) port.listarDatosDiaConsulta();
	}

	@Override
	public boolean ingresarClinica(String nombre, String desc, String dir, float costoT, float costoC, String link) throws NombreClinicaAlreadyExistsException_Exception {
		return port.ingresarClinica(nombre, desc, dir, costoT, costoC, link);
	}
	@Override
	public DtClinica seleccionarClinica(String nombre) {
		return (new PublisherService()).getPublisherPort().seleccionarClinica(nombre);
	}
	@Override
	public void setEstadoConsulta(EstadoConsulta state, String nombreConsulta) throws ConsultaNotFoundException_Exception {
		port.setEstadoConsulta(state, nombreConsulta);
	}
	@Override
	public DtConsultaDetalle listarDatosConsulta(String nomC) throws ConsultaNotFoundException_Exception {
		return port.listarDatosConsulta(nomC);
	}

	@Override
	public DtResConsultaDetalle listarDatosResConsultaCC(String nomC, int numReserva) throws ConsultaNotFoundException_Exception {
		return port.listarDatosResConsultaCC(nomC, numReserva);
	}

	@Override
	public Set<DtResConsulta> listarReservasConsulta(String nomC) throws ConsultaNotFoundException_Exception {
		return (Set<DtResConsulta>) port.listarReservasConsulta(nomC);
	}
	
	@Override
	public Set<DtConsulta> selecListaConsultaDelDia(String nomD) {
		return (Set<DtConsulta>) port.selecListaConsultaDelDia(nomD);
	}

	@Override
	public DtConsulta seleccionarConsulta(String nomC) throws ConsultaNotFoundException_Exception {
		return port.seleccionarConsulta(nomC);
	}

	@Override
	public Set<DtPaciente> listarPacientes() {
		return (Set<DtPaciente>) port.listarPacientes();
	}

	@Override
	public void reservarConsulta(String nicknamePaciente, String nomC, LocalDate fechaReserva, boolean aplicaBenef) throws BeneficioSinOrdenesException_Exception, ConsultaNotFoundException_Exception, NoNumberAvailableException_Exception, ReservaAlreadyExistsException_Exception, UserNotFoundException_Exception {
		port.reservarConsulta(nicknamePaciente, nomC, fechaReserva.toString() , aplicaBenef);
	}

	@Override
	public Set<DtConsulta> listarConsultas() {
		return (Set<DtConsulta>) port.listarConsultas();
	}

	@Override
	public void ingresarConsulta(String nomD, String med, String nomC, LocalDate fecha, int sala, int cantNum) throws ConsultaNombreAlreadyExistsException_Exception, FechaDiaSemanaInvalidaException_Exception, MedicoSinEspecialidadException_Exception, UserNotFoundException_Exception {
		port.ingresarConsulta(nomD, med, nomC, fecha.toString(), sala, cantNum);
	}

	@Override
	public Set<String> listarClinicas() {
		return (Set<String>) port.listarClinicas();
	}

	@Override
	public Set<String> listarEspecialidades() {
		HashSet<String> toReturn = new HashSet<String>();
		port.listarEspecialidades().getItem().forEach(str->toReturn.add(str));
	    return toReturn;
	}

	@Override
	public void ingresarDiaConsulta(String nombre, String descripcion, DtDiaSemana dia, LocalTime horaInicio,
			String nombreEspecialidad, String nombreClinica) throws DiaConsultaAlreadyExistsException_Exception, SpecialityNotFoundException_Exception {
		port.ingresarDiaConsulta(nombre, descripcion, dia, horaInicio.toString(), nombreEspecialidad, nombreClinica);
	}

	@Override
	public boolean ingresarClinica(String nombre, String desc, String dir, float costoT, float costoC, String link,
			String img) throws NombreClinicaAlreadyExistsException_Exception {
		if (img!=null && !img.isEmpty()) {
			//imagen
		}
		return port.ingresarClinica(nombre, desc, dir, costoT, costoC, link);
	}

	@Override
	public List<DtDiaConsulta> listarDiaConsultas() {
		return port.listarDiaConsultas().getItem();
	}
	
	@Override
	public List<String> getConsultasPorEspecialidad(String especialidad) {
		return (List<String>) port.getConsultasPorEspecialidad(especialidad);
	}
}
