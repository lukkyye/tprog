package com.gateway.connectors;

import java.util.Set;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.gateway.interfaces.IClinicController;
import com.soap.DtClinica;
import com.soap.DtConsulta;
import com.soap.DtConsultaDetalle;
import com.soap.DtDiaConsulta;
import com.soap.DtDiaSemana;
import com.soap.DtPaciente;
import com.soap.DtResConsultaDetalle;
import com.soap.EstadoConsulta;
import com.soap.HashSet;
import com.soap.LocalDate;
import com.soap.LocalTime;
import com.soap.Publisher;
import com.soap.PublisherService;
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
	public boolean ingresarClinica(String nombre, String desc, String dir, float costoT, float costoC, String link) {
		return port.ingresarClinica(nombre, desc, dir, costoT, costoC, link);
	}
	@Override
	public DtClinica seleccionarClinica(String nombre) {
		return (new PublisherService()).getPublisherPort().seleccionarClinica(nombre);
	}
	@Override
	public void setEstadoConsulta(EstadoConsulta state, String nombreConsulta) {
		port.setEstadoConsulta(state, nombreConsulta);
	}
	@Override
	public DtConsultaDetalle listarDatosConsulta(String nomC) {
		return port.listarDatosConsulta(nomC);
	}

	@Override
	public DtResConsultaDetalle listarDatosResConsultaCC(String nomC, int numReserva) {
		return port.listarDatosResConsultaCC(nomC, numReserva);
	}

	@Override
	public Set<DtResConsulta> listarReservasConsulta(String nomC) {
		return (Set<DtResConsulta>) port.listarReservasConsulta(nomC);
	}
	
	@Override
	public Set<DtConsulta> selecListaConsultaDelDia(String nomD) {
		return (Set<DtConsulta>) port.selecListaConsultaDelDia(nomD);
	}

	@Override
	public DtConsulta seleccionarConsulta(String nomC) {
		return port.seleccionarConsulta(nomC);
	}

	@Override
	public Set<DtPaciente> listarPacientes() {
		return (Set<DtPaciente>) port.listarPacientes();
	}

	@Override
	public void reservarConsulta(String nicknamePaciente, String nomC, LocalDate fechaReserva, boolean aplicaBenef) {
		port.reservarConsulta(nicknamePaciente, nomC, fechaReserva, aplicaBenef);
	}

	@Override
	public Set<DtConsulta> listarConsultas() {
		return (Set<DtConsulta>) port.listarConsultas();
	}

	@Override
	public void ingresarConsulta(String nomD, String med, String nomC, LocalDate fecha, int sala, int cantNum) {
		port.ingresarConsulta(nomD, med, nomC, fecha, sala, cantNum);
	}

	@Override
	public Set<String> listarClinicas() {
		return (Set<String>) port.listarClinicas();
	}

	@Override
	public Set<String> listarEspecialidades() {
	    return new java.util.HashSet<>(
	        java.util.Arrays.asList(port.listarEspecialidades())
	    );
	}

	@Override
	public void ingresarDiaConsulta(String nombre, String descripcion, DtDiaSemana dia, LocalTime horaInicio,
			String nombreEspecialidad, String nombreClinica) {
		port.ingresarDiaConsulta(nombre, descripcion, dia, horaInicio, nombreEspecialidad, nombreClinica);
	}

	@Override
	public boolean ingresarClinica(String nombre, String desc, String dir, float costoT, float costoC, String link,
			String img) {
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
